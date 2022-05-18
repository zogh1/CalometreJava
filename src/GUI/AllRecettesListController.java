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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import service.RecetteService;


/**
 *
 * @author Ahmed Mahjoub
 */
public class AllRecettesListController implements   Initializable {

    @FXML
    private HBox RecettesLayout;

    
    private List<Recette> allRecettes;
    private RecetteService RecetteService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RecetteService = new RecetteService();
        allRecettes = new ArrayList<Recette>(RecetteService.fetchAllRecette());

        try {
            for (int i = 0; i < allRecettes.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("RecetteCard.fxml"));
                
                AnchorPane eventBox = loader.load();
                
                RecetteCardController RecetteCardController = loader.getController();
                RecetteCardController.setData(allRecettes.get(i));
                
                RecettesLayout.getChildren().add(eventBox);
            }
        } catch (IOException ioe) {
            System.out.println("Error While Adding All Recettes cards : " + ioe.getMessage());
        }
    }    
    
}
