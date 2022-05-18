/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Recette;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.RecetteService;

/**
 *
 * @author Ahmed Mahjoub
 */
public class RecetteCardController  implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private ImageView image;
    @FXML
    private Label categorie;
    @FXML
    private Label regime;
    


    private String[] colors = {
        "#2596be",
        "#154c79",
        "#063970",
        "#abdbe3",
        "#76b5c5",
        "#le8lb0",
        "#eeeee4"
    };
    private RecetteService serviceRecette;
    
  
    @FXML
    private VBox box;
 

    private int parsedId;
    @FXML
    private AnchorPane RecetteCardContainer;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceRecette  = new RecetteService();
       
    }



    @FXML
    private void delete(MouseEvent event) {
        parsedId = Integer.parseInt(id.getText());

        try {
          serviceRecette.DeleatRecette(parsedId);
            RecetteCardContainer.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void update(MouseEvent event) throws IOException {
        parsedId = Integer.parseInt(id.getText());
        Recette rec = serviceRecette.GetById(parsedId);

        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadUpdateRecetteView("UpdateRecette.fxml", rec);
    }

    public void setData(Recette recette) throws FileNotFoundException {

        this.id.setText("" + recette.getId());

        this.nom.setText(recette.getNom());

        this.regime.setText(recette.getRegime());

        this.categorie.setText(recette.getCategorie());


        Image i = new Image(new FileInputStream("C:\\Users\\wassim\\Documents\\NetBeansProjects\\Calometre\\src\\img\\" + recette.getImage()));

        image.setImage(i);
        box.setStyle("-fx-background-color: " + colors[(int) Math.random() * 7]);

   
    }

 

}
