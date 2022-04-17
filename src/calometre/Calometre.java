/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.product;
import service.productservice;

/**
 *
 * @author Souhail
 */
<<<<<<< Updated upstream
public class Calometre {
=======
public class Calometre extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Calometre.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/addcategory.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
>>>>>>> Stashed changes

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
<<<<<<< Updated upstream
        // TODO code application logic here
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

        //ps.paginationProd();
        ps.searchByCategory(7);

    }

}
=======
//        // TODO code application logic here

        launch(args);
    }

}
/*public class main extends Application {
        private Stage primaryStage;
        @Override
        public void start(Stage primaryStage) throws Exception {
            this.primaryStage = primaryStage;
            mainWindow();
        }
        private void mainWindow() {
            try {
                FXMLLoader loader = new FXMLLoader(main.class.getResource("../GUI/login.fxml"));
                AnchorPane pane = loader.load();
            } catch (IOException e) {
            }
            //To change body of generated methods, choose Tools | Templates.
        }
    };
}*/
>>>>>>> Stashed changes
