/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Event;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class TopEventCardController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label date_fin;
    @FXML
    private Label date_debut;
    @FXML
    private Label nb_applyed;
    @FXML
    private Label nombre_participants;
    @FXML
    private HBox box;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceEvent = new ServiceEvent();
    }

    @FXML
    private void showDetails(MouseEvent event) {
        int parsedId;
        try {
            calometre.Calometre.mainController.getMenuPane().getChildren().clear();

            parsedId = Integer.parseInt(id.getText());
            calometre.Calometre.mainController.loadEventDetails("Details.fxml", parsedId);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "=====" + id.getText());
        }
    }

    public void setData(Event event) throws FileNotFoundException {

        this.id.setText("" + event.getId());

        this.nom.setText(event.getNom());

        this.description.setText(event.getNom());

        this.date_fin.setText(event.getDate_debut());

        this.date_debut.setText(event.getDate_fin());

        String nbApplyed = "" + serviceEvent.getNombre_participants_byevent(event.getId());
        this.nb_applyed.setText(nbApplyed);

        this.nombre_participants.setText("" + event.getNombre_participants());

        Image i = new Image(new FileInputStream("C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\Event_images\\" + event.getImage()));

        image.setImage(i);
        box.setStyle("-fx-background-color: " + colors[(int) Math.random() * 7]);
    }

}
