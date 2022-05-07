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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class TopThreeEventsListController implements Initializable {

    @FXML
    private HBox eventsLayout;

    private List<Event> topThreeEvents;
    private ServiceEvent serviceEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceEvent = new ServiceEvent();
        topThreeEvents = new ArrayList<Event>(serviceEvent.getTopThreeEvents());

        try {
            for (int i = 0; i < topThreeEvents.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("TopEventCard.fxml"));
                
                AnchorPane eventBox = loader.load();
                
                TopEventCardController topEventCardController = loader.getController();
                topEventCardController.setData(topThreeEvents.get(i));
                
                eventsLayout.getChildren().add(eventBox);
            }
        } catch (IOException ioe) {
            System.out.println("Error While Adding Top Three Events cards : " + ioe.getMessage());
        }
    }

}
