/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Reclamation;
import entity.user;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceReclamation;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class Calometre extends Application {

    public static Stage primaryStage;
    //aatini code mtaa ajout reclamaion

    @Override
    public void start(Stage primaryStage) throws Exception {
        ServiceReclamation fn = new ServiceReclamation();
        Reclamation rec = new Reclamation();

        userservice us = new userservice();
        user u = new user();
//        u.setEmail("rym.baccouri@esprit.tn");
//        u.setPassword("calometre222");
//        us.login(u);
        

        Calometre.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
