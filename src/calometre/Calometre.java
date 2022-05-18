/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import GUI.MainMenuController;
import entity.user;
import interfacee.userInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class Calometre extends Application {

    public static Stage primaryStage;
    public static final String CURRENCY = "TND";
    public static MainMenuController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        userInterface us = new userservice();
        user test = new user();
//        us.sendresetCode("crinnxx@gmail.com");

        Calometre.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //
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
