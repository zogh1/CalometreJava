/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Cart;
import entity.CartItem;
import entity.category;
import entity.exercice;
import entity.product;
import entity.typeExercice;
import entity.user;
import interfacee.userInterface;
import java.util.List;
import service.cartService;
import service.categoryservice;
import service.exerciceService;
import service.productservice;
import service.typeExerciceService;
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
       
   typeExerciceService fn1 = new typeExerciceService();
    typeExercice tex = new typeExercice();
     exerciceService fn = new exerciceService();
    exercice ex = new exercice();
   fn.searchExercice("az");
//    String nom = "hi";
//    tex.setNom(nom);
//    fn1.addType(tex);
//fn1.readType();
   
    
}
}

