/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.user;
<<<<<<< HEAD
import interfacee.userInterface;
import javax.mail.MessagingException;
import service.userservice;
import util.EmailSender;
=======
import java.sql.Connection;
import service.userservice;
import util.connexion;
>>>>>>> parent of 70de6e8 (Many functions done)

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
        userservice fn = new userservice();
        user test = new user();

<<<<<<< HEAD
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
=======
        test.setEmail("testmail");
        test.setPassword("password");

        fn.login(test);

//        test.setCountry_code("216");
//        test.setEmail("test");
//        test.setFirstname("hghghgh");
//        test.setLastname("rtrtt");
//        test.setPassword("ttttt");
//        test.setPhonenumber(1213);
//        test.setProfile_picture("fghfhgf");
//        test.setRoles("tttt");
//       fn.deleteuser(32);
        /*user edit =new user();

        edit.setCountry_code("216");
        edit.setEmail("test");
        edit.setFirstname("hghghgh");
        edit.setLastname("rtrddtt");
        edit.setPassword("ttttt");
        edit.setPhonenumber(1213);
        edit.setProfile_picture("fghfhgf");
        edit.setRoles("tttt");
        edit.setId(28);
        fn.updateuser(edit);*/
>>>>>>> parent of 70de6e8 (Many functions done)
    }

}
