/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Event;
import entity.Post;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ServiceEvent;
import service.ServicePost;
import sun.applet.Main;

/**
 *
 * @author wassim
 */
public class Calometre extends Application {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root =FXMLLoader.load(getClass().getResource("../GUI/mainMenu.fxml"));
           primaryStage.setTitle("calometre") ; 
                primaryStage.setScene (new Scene(root,1315,600)) ; 
                     primaryStage.show () ; 
                     
 
        /*      try {
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/MenuGusest.fxml"));
            Scene scene = new Scene(root);
//            scene.getStylesheets().add(getClass().getResource("").toExternalForm());
           primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
        
        
    }       
}
