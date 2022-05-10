/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Api.twilioSmsApi;
import entity.Event;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class EventCardController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private ImageView image;
    @FXML
    private Label description;
    @FXML
    private Label date_fin;
    @FXML
    private Label date_debut;
    @FXML
    private Label nb_applyed;
    @FXML
    private Label nombre_participant;

    private String[] colors = {
        "#2596be",
        "#154c79",
        "#063970",
        "#abdbe3",
        "#76b5c5",
        "#le8lb0",
        "#eeeee4"
    };
    private ServiceEvent serviceEvent;
    
    private twilioSmsApi serviceSMS;
    @FXML
    private VBox box;
    @FXML
    private Button applyButton;

    private int parsedId;
    @FXML
    private AnchorPane eventCardContainer;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceEvent = new ServiceEvent();
        serviceSMS = new twilioSmsApi();
    }

    @FXML
    private void apply(MouseEvent event) {

        try {
            parsedId = Integer.parseInt(id.getText());
            Event ev = serviceEvent.getEventById(parsedId);
            serviceEvent.applyToEvent(5, parsedId);
            serviceSMS.sendMessage("Hello , You just applied to '"+ev.getNom()+"' event don't miss it .Dont't miss it !!! Meet you there on: "+ev.getDate_debut(), "+21650310220");

            String nbApplyed = "" + serviceEvent.getNombre_participants_byevent(parsedId);
            this.nb_applyed.setText(nbApplyed);
            setVisibility();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //apply user to event
        //update front ++nb applyed
        // il me reste le qrcode
        //send mail :D 
    }

    @FXML
    private void delete(MouseEvent event) {
        parsedId = Integer.parseInt(id.getText());

        try {
            serviceEvent.deleteEvent(parsedId);
            eventCardContainer.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void update(MouseEvent event) throws IOException {
        parsedId = Integer.parseInt(id.getText());
        Event ev = serviceEvent.getEventById(parsedId);

        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadUpdateEventView("updateEvent.fxml", ev);
    }

    public void setData(Event event) throws FileNotFoundException {

        this.id.setText("" + event.getId());

        this.nom.setText(event.getNom());

        this.description.setText(event.getNom());

        this.date_fin.setText(event.getDate_debut());

        this.date_debut.setText(event.getDate_fin());

        String nbApplyed = "" + serviceEvent.getNombre_participants_byevent(event.getId());
        this.nb_applyed.setText(nbApplyed);

        this.nombre_participant.setText("" + event.getNombre_participants());

        Image i = new Image(new FileInputStream("C:\\Users\\wassim\\Desktop\\Pidev3A\\Calometre\\public\\uploads\\Event_images\\" + event.getImage()));

        image.setImage(i);
        box.setStyle("-fx-background-color: " + colors[(int) Math.random() * 7]);

        setVisibility();
    }

    private void setVisibility() {
        int parsedId;
        try {
            parsedId = Integer.parseInt(id.getText());

            if (serviceEvent.user_is_applyed_toevent(5, parsedId)) {
                applyButton.setVisible(false);
            } else {
                applyButton.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
