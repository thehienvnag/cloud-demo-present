package com.mycompany.java.web.demo.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author KhoaPHD
 */
public class CartObject implements Serializable {
    
    private Map<String, Integer> items;

    public CartObject() {
    }
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String title) {
        if (title == null) {
            return;
        }
        
        if (title.trim().isEmpty()) {
            return;
        }
        
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        
        int quantity = 1;
        if (this.items.containsKey(title)) {
            quantity = this.items.get(title) + 1;
        }
        
        this.items.put(title, quantity);
    }
    
    public void removeItemFromCart(String title) {
        if (this.getItems() == null) {
            return;
        }
        
        if (this.getItems().containsKey(title)) {
            this.getItems().remove(title);
            if (this.getItems().isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public void updateQuantity(String title, int quantity) {
        if (this.getItems() == null) {
            return;
        }
        
        if (this.getItems().containsKey(title)) {
            if (quantity == 0) {
                removeItemFromCart(title);
            } else if (quantity > 0) {
                this.getItems().put(title, quantity);
            }
        }
    }
}