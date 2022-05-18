/*
 

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Event;
import entity.Recette;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.RecetteService;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class RecettesViewController implements Initializable {

    @FXML
    private HBox allRecettesLayout;
    @FXML
    private Label RecetteNumberLabel;

    private List<Recette> allRecettes;

    private RecetteService recetteService = new RecetteService();

    private int numberOfRecettes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AllRecettesList.fxml"));

            AnchorPane allRecettesBox = loader.load();

            allRecettesLayout.getChildren().add(allRecettesBox);

            // number Of Events displayed
            allRecettes = new ArrayList<Recette>(recetteService.fetchAllRecette());
            numberOfRecettes = allRecettes.size();
            RecetteNumberLabel.setText("");
            RecetteNumberLabel.setText("(" + numberOfRecettes + ")");

        } catch (IOException ioe) {
            System.out.println("Error While Adding all cards in Recettes View : " + ioe.getMessage());
        }
    }

    @FXML
    private void goToAddRecetteForm(ActionEvent event) throws IOException {
         calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("AddRecette.fxml");
      
}
}