package org.binar.movieticketreservation.service;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperPrint;

@Service
public interface InvoiceService {
    JasperPrint generateJasperPrint(String transactionId) throws Exception;
}
