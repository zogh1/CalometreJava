/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Aliment;
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
import entity.Recette;
import service.ServiceEvent;
import service.ServicePost;
import entity.Reclamation;
import entity.Reponse;
import entity.exercice;
import entity.typeExercice;
import interfacee.AlimentInterface;
import interfacee.RecetteInterface;
import java.util.ArrayList;
import java.util.List;
import service.AlimentService;
import service.RecetteService;
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

        Connection cnx = connexion.getInstance().getCnx();

        /*List<Aliment> listAliment = new ArrayList();
        AlimentInterface RI = new AlimentService();

        listAliment = RI.fetchAllAliment();
        float b = 223;

        Aliment Test = new Aliment("ghgh", b, "TEST", "TEST");

        RI.DeleatAliment(3);
        RI.UpdateAliment(Test,4);
        System.out.println(listAliment);*/

//        List<Recette> listRecette =new ArrayList();
//        
//          RecetteInterface RI = new RecetteService();
//          listRecette=RI.fetchAllRecette();
//               
//      RI.DeleatRecette(5);
//     
//       
//     
//     
//       
//    
//     
//       Recette Test = new Recette("KHALED","AHMMED","TEST","TEST");
//      
//
//        System.out.println(listRecette);
        
        
        
        
        
        
        
        
        
        
//  userservice fn = new userservice();
//        user test = new user();

//        test.setEmail("test111");
//        test.setPassword("ttttt");
//
//        fn.login(test);

       





//       test.setCountry_code("216");
//        test.setEmail("test111");
//        test.setFirstname("hghghgh");
//        test.setLastname("rtrtt");
//        test.setPassword("ttttt");
//        test.setPhonenumber(1213);
//        test.setProfile_picture("fghfhgf");
//        test.setRoles("tttt");
//        
//        fn.createuser(test);
//     fn.findById(34);
//        user edit =new user();

//        edit.setCountry_code("216");
//        edit.setEmail("kahleeed");
//        edit.setFirstname("khaled");
//        edit.setLastname("rtrddtt");
//        edit.setPassword("ttttt");
//        edit.setPhonenumber(1213);
//        edit.setProfile_picture("fghfhgf");
//        edit.setRoles("tttt");
//        edit.setId(35);
//        fn.updateuser(edit);
//        seif ajout
//        
//categoryservice cf = new categoryservice();
//        category cat = new category();
//         cat.setName("pidev");
//     cf.createcategory(cat);
//cf.getallcategory();
 //cf.deletecategory(8);
//cat.setName("banasfrgrerne");
//cat.setId(6);
//cf.updatecategory(cat);

//        productservice ps = new productservice();
//product p = new product();
//category cat = new category();
//       cat.setId(8);
//       p.setName("iphone12promax");
//
//        p.setPrice(1243);
//        p.setDescription("abc");
//        p.setQuantity(20);
//        p.setImage("ttttt");
//        p.setId(9);
//        ps.deleteproduct(9);

//ps.getallproduct();
      exerciceService es = new exerciceService();
                  exercice exc = new exercice();
        
//        typeExercice t = new typeExercice();
//          exc.setId(13);
//         t.setId(5);
//          exc.setNom("yassine");
//          exc.setVideo("gh");
//         exc.setDescription("helddlo");
//         
//         exc.setObjectif("dgfghfhg");
//        
 //        es.addExercice(t, exc);
//       es.editExercice(exc);
        //es.readExercice();
        es.deleteExercice(12);
//         
       

      













//        ServiceEvent se = new ServiceEvent();
//      
//        Event e = se.getEventById(17) ;
//        System.out.println(e);
//        //   Event e = new Event("New Event","02/22/2022","02/23/2022","Nullam iaculis lacus non lectus venenatis tincidunt. Pellentesque est lectus, semper vitae aliquam in, pretium at libero. Nulla sed mauris neque. Nam eget pulvinar turpis, nec iaculis diam. Proin nec cursus libero",10,"moknin","QSVDJEAC41J2G5X4NJ.jpg");
//        Post p = new Post ("New post","2022-03-22 ","aaaa wassim kifeh",e) ; 
////se.getallEvent();
//     ServicePost sp = new ServicePost(); 
//     // sp.getallPost(); 
//      //se.getEventById(17);
//      // se.createEvent(e);
//      sp.createPost(p) ;  
//        // TODO code application lnogic here








//ServiceReclamation fn = new ServiceReclamation();
//    Reclamation rec = new Reclamation();
//     ServiceReponse f = new ServiceReponse();
//       Reponse rep = new Reponse();
//      Reclamation rec = new Reclamation();

        //ajout
//         rec.setEmail("khaledesprit");
//        rec.setMessage("helllo");
//        rec.setType("coach");
//
//        fn.editReclamation(13);
        
        /* rec.setId(13);
        rec.setEmail("khaled esprit");
        rec.setMessage("message");
        rec.setType("tbnj");*/

       // fn.editReclamation(rec);
        //affichage
        //fn.readReclamation();
        // rec.setId(36);
        /* rec.setId(36);
       fn.deleteReclamation(35);
         */
        //delete 
        //ajout
       // rep.setReponse("ok validation ");

        //rep.setId(13);
        
        
       // f.createReponse(rep);
    }

}
