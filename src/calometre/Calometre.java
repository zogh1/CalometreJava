/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.user;
import interfacee.userInterface;
import javax.mail.MessagingException;
import service.userservice;
import util.EmailSender;

/**
 *
 * @author Souhail
 */
public class Calometre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        // TODO code application logic here

        userInterface fn = new userservice();
        user user = new user();

        user.setEmail("souhail");
        user.setPassword("password");
        fn.login(user);
//        System.out.println(fn.verifyEmailExistance("souhail"));
        // fn.unbanUser(36);
        //fn.updatePassword("hello", "password");
        // fn.searchUser("souhail");
        // fn.orderUser();
//        user.setCountry_code("216");
//        user.setEmail("test111");
//        user.setFirstname("hghghgh");
//        user.setLastname("rtrtt");
//        user.setPassword("ttttt");
//        user.setPhonenumber(1213);
//        user.setProfile_picture("fghfhgf");
//        user.setRoles("tttt");
//        fn.createuser(user);
        //  fn.countuserbyRole("tttt");
        EmailSender.sendEmailWithAttachments("crinnxx@gmail.com", "test", "message");
    }

}
