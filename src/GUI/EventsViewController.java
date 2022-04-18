/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class EventsViewController implements Initializable {

    @FXML
    private HBox topThreeEventsLayout;
    @FXML
    private HBox allEventsLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TopThreeEventsList.fxml"));

            AnchorPane topThreeEventsBox = loader.load();

            topThreeEventsLayout.getChildren().add(topThreeEventsBox);

        } catch (IOException ioe) {
            System.out.println("Error While Adding Top Three Events cards in Events View : " + ioe.getMessage());
        }
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AllEventsList.fxml"));

            AnchorPane allEventsBox = loader.load();

            allEventsLayout.getChildren().add(allEventsBox);

        } catch (IOException ioe) {
            System.out.println("Error While Adding all cards in Events View : " + ioe.getMessage());
        }
    }

}
