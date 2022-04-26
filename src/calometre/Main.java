/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.user;
import interfacee.userInterface;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        userInterface us = new userservice();
        user test = new user();
        test.setPassword("password");
        test.setEmail("souhail.krissaane@esprit.tn");
        test.setFirstname("hghghgh");
        test.setLastname("rtrtt");
        test.setRoles("tttt");
        test.setPhonenumber(1213);
        test.setProfile_picture("fghfhgf");
        test.setCountry_code("216");

        us.createuser(test);

        //us.sendresetCode("souhail.krissaane@esprit.tn");
    }

}
