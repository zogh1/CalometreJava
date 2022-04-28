/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Souhail
 */
public class Calometre extends Application {

    public static Stage primaryStage;
    public static final String CURRENCY = "TND";
    @Override
    public void start(Stage primaryStage) throws Exception {
        Calometre.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/products.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //Calometre.primaryStage.setFullScreen(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}