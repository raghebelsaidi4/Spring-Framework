package com.system.beans;

import com.system.dao.ReportDAO;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlDBDAO implements ReportDAO {

    @Override
    public void getData() {
        System.out.println("getting data from MysqlDB");
    }
}
