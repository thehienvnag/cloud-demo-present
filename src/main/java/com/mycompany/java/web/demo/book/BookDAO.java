package com.mycompany.java.web.demo.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import com.mycompany.java.web.demo.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class BookDAO implements Serializable {
    
    private final boolean AVAILABLE = true;
    private List<String> bookList;

    public List<String> getBookList() {
        return bookList;
    }
    
    public void loadAvailableBooks()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT bookTitle "
                        + "FROM Book "
                        + "WHERE isAvailable = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, AVAILABLE);
                
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    String bookTitle = rs.getString("bookTitle");
                    
                    if (this.bookList == null) {
                        this.bookList = new ArrayList<>();
                    }
                    
                    this.bookList.add(bookTitle);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean isAvailable(String bookTitle)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isAvailable = false;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT isAvailable "
                        + "FROM Book "
                        + "WHERE bookTitle = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookTitle);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    isAvailable = rs.getBoolean("isAvailable");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isAvailable;
    }
}