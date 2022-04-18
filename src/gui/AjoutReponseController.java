/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import entity.Reponse;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.ServiceReponse;
import util.connexion;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class AjoutReponseController implements Initializable {

    @FXML
    private TextField tf_date;
    @FXML
    private ComboBox<Integer> combo_id;
    @FXML
    private TextField tf_reponse;

    /**
     * Initializes the controller class.
     */
    ServiceReponse sr= new ServiceReponse();
    Reponse rep= new Reponse();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req="select id from reclamation";
            PreparedStatement pst = connexion.getInstance().getCnx()   
                   .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
                list.add(rs.getInt("id"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterReponse(ActionEvent event) {
        String date= tf_date.getText();
    int idRep=combo_id.getSelectionModel().getSelectedItem();
    String reponse=tf_reponse.getText();
    if (date.isEmpty() || reponse.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        }
    else{
    
    rep.setDate(date);
       rep.setRepondre_id(idRep);
       rep.setReponse(reponse);
       Reclamation rec= new Reclamation();
       rec.setId(idRep);
      sr.createReponse(rep,rec);
    
    }
    
    
    }
    
}
