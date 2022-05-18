/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Recette;
import interfacee.RecetteInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.RecetteService;

/**
 *
 * @author Ahmed Mahjoub
 */
public class RecetteController implements Initializable {

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCategory.setItems(categorieOptions);
        comboBoxRegime.setItems(regimeOptions);
        
    
    }
        //var
    RecetteInterface sp = new RecetteService();
    ObservableList<String> categorieOptions = 
    FXCollections.observableArrayList(
        "Sucrée",
        "Acide ",
        "Salé",
        "Amer",
        "Umami"
    );
     ObservableList<String> regimeOptions = 
    FXCollections.observableArrayList(
        "Hyperprotéiné",
        "Protéiné",
        "Dissocié",
        "Hypocalorique",
        "Végétarien",
        "Anticellulite",
        "Sans sel",
        "Hypoglucidique"
    );
        
        
    
    //widgets
    @FXML
    private TextField nomTF;
    @FXML
    private TextField idTF;
    @FXML
    private ComboBox comboBoxCategory ;
    @FXML
    private TextField imageTF;
     @FXML
    private ComboBox comboBoxRegime ;
      @FXML
    private Label idLabel ;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void save(MouseEvent event) {

       
        String name = nomTF.getText();
        String regime = comboBoxRegime.getValue().toString();
        String categorie = comboBoxCategory.getValue().toString();
       

      if (name.length()<3 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Recette name must contain at least 3 caracters");
            alert.show();

        }
         else {
           sp.addRecette(new Recette(name,regime,categorie,"test"));
         
          

        }
    }
         @FXML
          private void update(MouseEvent event) {

      
       String name = nomTF.getText();
        String regime = comboBoxRegime.getValue().toString();
        String categorie = comboBoxCategory.getValue().toString();
        int id = Integer.parseInt(idLabel.getText());

     
      
        
     if (name.length()<3 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Recette name must contain at least 3 caracters");
            alert.showAndWait();

        }
        else {
           sp.UpdateRecette(new Recette(name,regime,categorie,"test"),id);
           
          

        }

    
  
       
        
    }
     void setIDLabel(int  id) {
         
         idLabel.setText(Integer.toString(id));
         
    }
      
    



    
   
    
    
}
