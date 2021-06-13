package com.mycompany.java.web.demo.account;

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
public class AccountDAO implements Serializable {
    
    private final boolean ACTIVE = true;
    private final boolean NOT_ACTIVE = false;
    
    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT username "
                        + "FROM Account "
                        + "WHERE username = ? AND password = ? AND isActive = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setBoolean(3, ACTIVE);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }
    
    public String getFullName(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String fullName = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT fullName "
                        + "FROM Account "
                        + "WHERE username = ? AND isActive = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setBoolean(2, ACTIVE);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("fullName");
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
        return fullName;
    }
    
    public boolean isAdmin(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean role = false;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT isAdmin "
                        + "FROM Account "
                        + "WHERE username = ? AND isActive = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setBoolean(2, ACTIVE);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getBoolean("isAdmin");
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
        return role;
    }
    
    private List<AccountDTO> accountList;

    public List<AccountDTO> getAccountList() {
        return accountList;
    }
    
    public void searchFullName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, fullName, isAdmin "
                        + "FROM Account "
                        + "WHERE fullname LIKE ? AND isActive = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setBoolean(2, ACTIVE);
                
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    boolean role = rs.getBoolean("isAdmin");
                    AccountDTO dto = new AccountDTO(username, password, fullName, role);
                    
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    
                    this.accountList.add(dto);
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
    
    public boolean deleteAccount(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET isActive = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, NOT_ACTIVE);
                stm.setString(2, username);
                
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
    
    public boolean updateAccount(String username, String password, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "UPDATE Account "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ? AND isActive = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                stm.setBoolean(4, ACTIVE);
                
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
    
    public boolean insertNewAccount(AccountDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO "
                        + "Account(username, password, fullName, isAdmin, isActive) "
                        + "VALUES(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                stm.setBoolean(5, ACTIVE);
                
                int rowEffect = stm.executeUpdate();
                if (rowEffect > 0) {
                    return true;
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
        return false;
    }
}