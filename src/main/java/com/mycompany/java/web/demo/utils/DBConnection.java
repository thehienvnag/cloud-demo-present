package com.mycompany.java.web.demo.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author KhoaPHD
 */
public class DBConnection implements Serializable {
    
//    public static Connection makeConnection()
//            throws NamingException, SQLException {
//        Context context = new InitialContext();
//        Context tomContext = (Context) context.lookup("java:comp/env");
//        DataSource ds = (DataSource) tomContext.lookup("SE140609DS");
//        Connection con = ds.getConnection();
//        return con;
//    }
    
    public static Connection makeConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connString = System.getenv("SQLCONNSTR_AwsDb");
            con=DriverManager.getConnection(connString);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return con;
    }
}