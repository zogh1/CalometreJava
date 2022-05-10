/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.ServiceReclamation;
import util.connexion;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfdate;
    @FXML
    private ComboBox tftype;
    @FXML
    private TextField tfmessage;
    @FXML
    private Button btnajouter;
    @FXML
    private ComboBox<Integer> combo_id;

    /**
     * Initializes the controller class.
     */
    
    Reclamation r = new Reclamation();
    ServiceReclamation sr = new ServiceReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tftype.getItems().removeAll(tftype.getItems());
        tftype.getItems().addAll("Food", "Coach","Exercice");
        
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
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    


    @FXML
    private void modifierRec(ActionEvent event) {
        try {
            
        sr.editReclamation(new Reclamation(combo_id.getSelectionModel().getSelectedItem(), tfemail.getText(), tfdate.getText(),tftype.getSelectionModel().getSelectedItem().toString(),tfmessage.getText()));       
        JOptionPane.showMessageDialog(null, "Reclamation modifié");
        
        
           calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("listerec.fxml");
        }catch (IOException ex) {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getId(MouseEvent event) {
        combo_id.setOnAction(e ->{
            String req="select email,date,type,message from reclamation where id=?";
            try {
                    PreparedStatement pst = connexion.getInstance().getCnx()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getString("email"));
                    System.out.println(rs.getString("date"));
                    tfemail.setText(rs.getString("email"));
                    tfdate.setText(rs.getString("date"));
                    tftype.setValue(rs.getString("type"));
                    tftype.setPromptText(rs.getString("type"));
                    tftype.setDisable(true);
                    tfmessage.setText(rs.getString("message"));
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
