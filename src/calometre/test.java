/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.category;
import entity.user;
import interfacee.userInterface;
import service.categoryservice;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
    category cat = new category();
    categoryservice fn = new categoryservice();
    
    fn.findByName("Food");

    
}
}
