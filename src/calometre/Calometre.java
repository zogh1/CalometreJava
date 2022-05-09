/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Souhail
 */
public class Calometre extends Application {

    public static Stage primaryStage;
    public static final String CURRENCY = "TND";
    long lastRefreshTime = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Calometre.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/BackOfficeProducts.fxml"));

        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

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
