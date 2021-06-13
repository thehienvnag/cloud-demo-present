package com.mycompany.java.web.demo.receipt;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author KhoaPHD
 */
public class ReceiptDTO implements Serializable {
    
    private int receiptId;
    private String username;
    private Timestamp shoppingDate;

    public ReceiptDTO() {
    }

    public ReceiptDTO(int receiptId, String username, Timestamp shoppingDate) {
        this.receiptId = receiptId;
        this.username = username;
        this.shoppingDate = shoppingDate;
    }

    /**
     * @return the receiptId
     */
    public int getReceiptId() {
        return receiptId;
    }

    /**
     * @param receiptId the receiptId to set
     */
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the shoppingDate
     */
    public Timestamp getShoppingDate() {
        return shoppingDate;
    }

    /**
     * @param shoppingDate the shoppingDate to set
     */
    public void setShoppingDate(Timestamp shoppingDate) {
        this.shoppingDate = shoppingDate;
    }
}