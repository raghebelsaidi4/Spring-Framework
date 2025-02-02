package com.system.service;

import com.system.dao.ReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Qualifier("mysqlDBDAO")
    @Autowired
    private ReportDAO reportDAO;

    public void generateReport() {
        reportDAO.getData();
        System.out.println("Report generated ...");
    }

}
