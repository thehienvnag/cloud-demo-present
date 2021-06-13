package com.mycompany.java.web.demo.receiptdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import com.mycompany.java.web.demo.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class ReceiptDetailDAO implements Serializable {
    
    public boolean insertNewReceiptDetail(ReceiptDetailDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO "
                        + "ReceiptDetail(receiptId, bookTitle, quantity) "
                        + "VALUES(?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getReceiptId());
                stm.setString(2, dto.getBookTitle());
                stm.setInt(3, dto.getQuantity());
                
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