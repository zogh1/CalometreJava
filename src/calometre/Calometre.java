/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import java.sql.Connection;
import service.userservice;
import util.connexion;
import service.exerciceService;
import entity.category;
import entity.product;
import entity.user;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author Souhail
 */
public class Calometre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        userservice fn = new userservice();
        user test = new user();

        test.setEmail("testmail");
        test.setPassword("password");

        fn.login(test);

        exerciceService es = new exerciceService();

        es.deleteExercice(8);
//        userservice fn = new userservice();
//        user test = new user();

//        test.setEmail("testmail");
//        test.setPassword("password");
//
//        fn.login(test);

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
//        seif ajout
        categoryservice fn = new categoryservice();
        category test = new category();
//     ajout    test.setName("kiwi");
//        fn.createcategory(test);
//affichae fn.getallcategory();
//delete fn.deletecategory(8);
// update
//test.setName("banasfrgrerne");
//test.setId(6);
//fn.updatecategory(test);

productservice ps = new productservice();
product prod = new product();


ps.getallproduct();
        

        
    }

}
