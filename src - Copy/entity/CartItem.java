/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author seifd
 */
public class CartItem {

    /**
     * @return the product
     */
    public product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(product product) {
        this.product = product;
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
    private product product;
    private int quantity;
    
    
    
   
    
}

