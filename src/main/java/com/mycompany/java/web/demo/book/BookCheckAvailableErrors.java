package com.mycompany.java.web.demo.book;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author KhoaPHD
 */
public class BookCheckAvailableErrors implements Serializable {
    
    private List<String> notAvailableBooksList;

    public BookCheckAvailableErrors() {
    }

    /**
     * @return the notAvailableBooksList
     */
    public List<String> getNotAvailableBooksList() {
        return notAvailableBooksList;
    }
}