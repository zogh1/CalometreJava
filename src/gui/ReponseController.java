/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import entity.Reponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javax.swing.JOptionPane;
import service.ServiceReclamation;
import service.ServiceReponse;
import util.session;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ReponseController implements Initializable {

    
    @FXML
    private Button addreponse;
    @FXML
    private ListView<Reponse> listerep;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReponse sr = new ServiceReponse();
        List<Reponse> lrp = sr.readReponse();
        ObservableList<Reponse> data=FXCollections.observableArrayList(lrp);
        listerep.setItems(data);
                
    }

    @FXML
    private void ajouterReponse(ActionEvent event) {
     
        
        
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutReponse.fxml"));
            Parent root=loader.load();
            AjoutReponseController aac=loader.getController();
            listerep.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierReponse(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierReponse.fxml"));
            Parent root=loader.load();
            ModifierReponseController aac=loader.getController();
            listerep.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadReponses() {
        ServiceReponse sr = new ServiceReponse();
        ArrayList<Reponse> listeReponse = (ArrayList<Reponse>) sr.readReponse();

        ObservableList observableList = FXCollections.observableArrayList(listeReponse);
        listerep.setItems(observableList);

    }

    @FXML
    private void supprimerReponse(ActionEvent event) {
        Reponse R = new Reponse();
        R = listerep.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une reponse à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer la reponse?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ServiceReponse SR= new ServiceReponse();
                SR.deleteReponse(R.getId());
                JOptionPane.showMessageDialog(null, "Reponse supprimé");
                loadReponses();
            }
    }
    }

   

   
}
