package com.mycompany.java.web.demo.receipt;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.mycompany.java.web.demo.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class ReceiptDAO implements Serializable {
    
    public int generateReceiptId()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int receiptId = 1;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT MAX(receiptId) as receiptId "
                        + "FROM Receipt";
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    receiptId = rs.getInt("receiptId") + 1;
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
        return receiptId;
    }
    
    public boolean insertNewReceipt(ReceiptDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO "
                        + "Receipt(receiptId, username, shoppingDate) "
                        + "VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getReceiptId());
                stm.setString(2, dto.getUsername());
                stm.setTimestamp(3, dto.getShoppingDate());
                
                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}