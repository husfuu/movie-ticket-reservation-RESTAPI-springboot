package org.binar.movieticketreservation.controller.controllerimpl;

import javax.servlet.http.HttpServletResponse;

import org.binar.movieticketreservation.service.serviceimpl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/api/v1")
public class InvoiceControllerImpl {
    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Autowired
    private HttpServletResponse response;

    @GetMapping(value = "/invoices/{transactionId}")
    public void getInvoiceTicket(@PathVariable("transactionId") String transactionId) throws Exception {
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=invoice.pdf;");
            JasperPrint jasperPrint = invoiceServiceImpl.generateJasperPrint(transactionId);

            JasperExportManager.exportReportToPdfStream(jasperPrint,
                    response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
