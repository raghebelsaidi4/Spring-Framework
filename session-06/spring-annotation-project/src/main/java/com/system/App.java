package com.system;


import com.system.config.AppConfig;
import com.system.service.ReportService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ReportService service = context.getBean(ReportService.class);
        service.generateReport();

    }
}
