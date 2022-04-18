/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.ServiceEvent;

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
    @FXML
    private Label EventNumberLabel;
    
    private List<Event> allEvents;

    private ServiceEvent serviceEvent= new ServiceEvent();
    
    private int numberOfEvents;
    
    


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
            
            // number Of Events displayed
        allEvents = new ArrayList<Event>(serviceEvent.getallEvent());
        numberOfEvents =  allEvents.size();
        EventNumberLabel.setText("");
        EventNumberLabel.setText("("+numberOfEvents+")");
        
        
        } catch (IOException ioe) {
            System.out.println("Error While Adding all cards in Events View : " + ioe.getMessage());
        }
    }

    @FXML
    private void goToAddEventForm(ActionEvent event) throws IOException  {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addEvent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(root1));  
        secondaryStage.setTitle("Add Event") ; 
           
        secondaryStage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
        
        
    }

}
