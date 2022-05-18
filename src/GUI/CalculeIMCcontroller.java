/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Recette;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import service.RecetteService;

/**
 *
 * @author Ahmed Mahjoub
 */
public class CalculeIMCcontroller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        
    }
       private List<Recette> allRecettes;
    private RecetteService RecetteService;
    @FXML
    private TextField poidsTF;
    @FXML
    private TextField tailleTF;
    @FXML
    private Label imcDescriptionLabel;
     @FXML
    private Button CalculerIMC;
       @FXML
    private Label regimeLabel;
           @FXML
    private Label calculimc;
            @FXML
    private Button Recettes;
            @FXML
            HBox hbox;
            @FXML
            AnchorPane troisRecettesAnchorPane;
            @FXML
            ScrollPane scrollPane;
     
     
      @FXML
      private void save(MouseEvent event) {

      RecetteService = new RecetteService();
        allRecettes = new ArrayList<Recette>(RecetteService.fetchAllRecette());
        int count = 0;
       
    
       
        

        if (poidsTF.getText().isEmpty() || tailleTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.show();

        }
        else  {
          try {
        float  poids = Float.parseFloat(poidsTF.getText());
        float  taille = Float.parseFloat(tailleTF.getText());
        float IMC = poids/(taille*taille);
        System.out.println(IMC);
        
        if((IMC > 16.5) && (IMC < 18.5)){
            regimeLabel.setText("Hyperprotéiné");
           String desc ="Votre IMC est de"+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de maigreur nous vous proposons de suivre un régime "+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
          
             
                         
      
            
        
        }
        else if ((IMC > 18.5) && (IMC < 25))
        { regimeLabel.setText("Normal");
        String desc ="Votre IMC est de "+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de corpulance normal "+"\n"+"nous ne proposons pas  de suivre un régime "+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
        
        
        } else if ((IMC > 25) && (IMC < 30))
        { regimeLabel.setText("Dissocié");
        String desc ="Votre IMC est de "+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de surpoids"+"\n"+" nous vous proposons de suivre un régime "+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
        
        
        }else if ((IMC > 25) && (IMC < 30))
        { regimeLabel.setText("Hypocalorique");
        String desc ="Votre IMC est de "+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de Obésité modérée"+"\n"+" nous vous proposons de suivre un régime "+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
        
        
        }else if ((IMC > 25) && (IMC < 30))
        { regimeLabel.setText("Hypoglucidique");
        String desc ="Votre IMC est de "+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de Obésité sévére"+"\n"+" nous vous proposons de suivre un régime "+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
        
        
        }else if ((IMC > 25) && (IMC < 30))
        { regimeLabel.setText("Anticellulite");
        String desc ="Votre IMC est de"+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de Obésité massive"+"\n"+ "nous vous proposons de suivre un régime"+regimeLabel.getText();
            this.imcDescriptionLabel.setText(desc);
        
        
        }else if (IMC<18.5)
        { regimeLabel.setText("Dissocié");
        String desc ="Votre IMC est de"+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de Maigreur massive"+"\n"+ "nous vous proposons  d'aller consulter un medecin";
            this.imcDescriptionLabel.setText(desc);
        
        
        }else if (IMC > 30)
        { regimeLabel.setText("Obésité Mortel");
        String desc ="Votre IMC est de"+Float.toString(IMC)+"\n"+" vous etes  dans un intervalle de Obésité mortel"+"\n"+ "nous vous proposons d'aller consulter un medecin";
            this.imcDescriptionLabel.setText(desc);
        
        
        }
             try {
            for (int i = 0; i < allRecettes.size(); i++) {
                if(count<3 && allRecettes.get(i).getCategorie().equalsIgnoreCase(regimeLabel.getText()))
                {
                    System.out.println(allRecettes.get(i).getCategorie());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("RecetteCard.fxml"));
                
                AnchorPane troisRecettesAnchorPane = loader.load();
                
                RecetteCardController RecetteCardController = loader.getController();
                RecetteCardController.setData(allRecettes.get(i));
                
                hbox.getChildren().add(troisRecettesAnchorPane);
                }
            
            }
        } catch (IOException ioe) {
            System.out.println("Error While Adding All Recettes cards : " + ioe.getMessage());
        }
        
            
        
      
      } catch (NumberFormatException ex) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Entrer une taille et un poids valides ");
            alert.show();
      }
        }
       
    }
     

     


}

