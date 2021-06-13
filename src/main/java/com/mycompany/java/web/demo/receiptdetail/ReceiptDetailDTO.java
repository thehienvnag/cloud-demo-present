package com.mycompany.java.web.demo.receiptdetail;

import java.io.Serializable;

/**
 *
 * @author KhoaPHD
 */
public class ReceiptDetailDTO implements Serializable {
    
    private int receiptId;
    private String bookTitle;
    private int quantity;

    public ReceiptDetailDTO() {
    }

    public ReceiptDetailDTO(int receiptId, String bookTitle, int quantity) {
        this.receiptId = receiptId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
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
     * @return the bookTitle
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}