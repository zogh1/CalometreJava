/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Souhail
 */
public class Cart {

    /**
     * @return the items
     */
    public ArrayList<CartItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        double total=0;
       for(CartItem ci: this.getItems()){
       total+=ci.getQuantity()*ci.getProduct().getPrice();
       
       }
       return total;
    }



    /**
     * @return the user_cart_id
     */
    public int getUser_cart_id() {
        return user_cart_id;
    }

    /**
     * @param user_cart_id the user_cart_id to set
     */
    public void setUser_cart_id(int user_cart_id) {
        this.user_cart_id = user_cart_id;
    }

    private int id;
    private double total;
    private int user_cart_id;
    private ArrayList<CartItem> items;
    public Cart() {
    }

    public Cart(int id, double total,int user_cart_id, ArrayList<CartItem> items) {
       this.id = id;
       this.total = total;
       this.user_cart_id = user_cart_id;
       this.items = items;
    }
   
    
}
