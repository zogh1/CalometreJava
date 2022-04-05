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
import entity.Event;
import entity.Post;
import service.ServiceEvent;
import service.ServicePost;
import entity.Reclamation;
import entity.Reponse;
import service.ServiceReclamation;
import service.ServiceReponse;

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
        categoryservice cf = new categoryservice();
        category cat = new category();
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
        

        
        ServiceEvent se = new ServiceEvent();
      
        Event e = se.getEventById(17) ;
        System.out.println(e);
        //   Event e = new Event("New Event","02/22/2022","02/23/2022","Nullam iaculis lacus non lectus venenatis tincidunt. Pellentesque est lectus, semper vitae aliquam in, pretium at libero. Nulla sed mauris neque. Nam eget pulvinar turpis, nec iaculis diam. Proin nec cursus libero",10,"moknin","QSVDJEAC41J2G5X4NJ.jpg");
        Post p = new Post ("New post","2022-03-22 ","aaaa wassim kifeh",e) ; 
//se.getallEvent();
     ServicePost sp = new ServicePost(); 
     // sp.getallPost(); 
      //se.getEventById(17);
      // se.createEvent(e);
      sp.createPost(p) ;  
        // TODO code application lnogic here
        //ServiceReclamation fn = new ServiceReclamation();
//        Reclamation rec = new Reclamation();
        ServiceReponse f = new ServiceReponse();
        Reponse rep = new Reponse();
        Reclamation rec = new Reclamation();

        //ajout
        /* rec.setEmail("rymm");
        rec.setMessage("mehjgucugcssage");
        rec.setType("coach");

        fn.createReclamation(rec);*/
        //edit
        /* rec.setId(39);
        rec.setEmail("test");
        rec.setMessage("message");
        rec.setType("ttttttt");

        fn.editReclamation(rec);*/
        //affichage
        //fn.readReclamation();
        // rec.setId(36);
        /* rec.setId(36);
       fn.deleteReclamation(35);
         */
        //delete 
        //ajout
        
        rep.setReponse("rymm");

        rep.setId(52);
        
        f.editReponse(rep);

    }

}
