/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.exercice;
import entity.typeExercice;
import entity.user;
import interfacee.userInterface;
import service.exerciceService;
import service.typeExerciceService;
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
//        us.sendresetCode("crinnxx@gmail.com");
        exerciceService fn = new exerciceService();
        exercice test1 = new exercice();

        typeExerciceService fn1 = new typeExerciceService();
        typeExercice test2 = new typeExercice();
        fn.readExercice();

//        test.setPassword("password");
//        test.setEmail("souhail.krissaane@esprit.tn");
//        test.setFirstname("hghghgh");
//        test.setLastname("rtrtt");
//        test.setRoles("tttt");
//        test.setPhonenumber(1213);
//        test.setProfile_picture("fghfhgf");
//        test.setCountry_code("216");
        /*List<user> li = us.pagination(2, 0);
        for (int i = 0; i < li.size(); i++) {

            System.out.println("*********");
            System.out.println(us.pagination(i + 1, i).get(i).getId());

            System.out.println(li.get(i).getFirstname());

        }*/
//        int size = 5;
//        int row = 0;
//        int nb = us.getRowCount() / size;
//
//        for (int i = 0; i <= nb + 2; i++) {
//            System.out.println("*********");
//            us.pagination(size, row);
//            row = row + size;
//
//        }
        /*int size = 5;
        int j = us.getRowCount() / size;
        int start = 0;
        for (int i = 0; i <= j - 1; i++) {
            us.pagination(size, start);
            start = start + size;
            System.out.println("*************");
        }*/
        // us.getalluser();
        //us.sendresetCode("souhail.krissaane@esprit.tn");
        System.err.println(us.CreateCaptchaValue());

    }

}
