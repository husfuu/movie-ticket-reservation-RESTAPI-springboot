package org.binar.movieticketreservation.controller.controllerimpl;

import javax.servlet.http.HttpServletResponse;

import org.binar.movieticketreservation.service.serviceimpl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/api/v1")
public class InvoiceControllerImpl {
    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Autowired
    private HttpServletResponse response;

    @Operation(
        summary = "Get invoice", 
        description = "Download invoice by transaction id", 
        tags = "Invoice",
        security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping(value = "/invoices/{transactionId}")
    public void getInvoiceTicket(@PathVariable("transactionId") String transactionId) throws Exception {
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=invoice.pdf;");
            JasperPrint jasperPrint = invoiceServiceImpl.generateJasperPrint(transactionId);
            System.out.println("di controller: " + jasperPrint);
            JasperExportManager.exportReportToPdfStream(jasperPrint,
                    response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
