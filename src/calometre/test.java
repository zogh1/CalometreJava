/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Cart;
import entity.CartItem;
import entity.category;
import entity.product;
import entity.user;
import interfacee.userInterface;
import java.util.List;
import service.cartService;
import service.categoryservice;
import service.productservice;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       
    Cart crt = new Cart();
   cartService fn2 = new cartService();
   CartItem crti = new CartItem();
        productservice fn = new productservice();
        product test = new product();
//    fn.searchProduct("hi");
//    
//    System.out.println(fn.searchProduct(""));
System.out.println(fn.searchByCategory("5"));
    
        }
    
    
}
