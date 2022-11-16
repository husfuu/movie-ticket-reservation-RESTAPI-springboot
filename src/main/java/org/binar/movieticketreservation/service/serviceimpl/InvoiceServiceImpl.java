package org.binar.movieticketreservation.service.serviceimpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    public InvoiceServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    // connect to database
    @Autowired
    private DataSource dataSource;

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JasperPrint generateJasperPrint(String transactionId) throws Exception {
        try {
            InputStream fileReport = new ClassPathResource("static/Invoice.jasper").getInputStream();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("transaction_id", transactionId);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());

            return jasperPrint;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
