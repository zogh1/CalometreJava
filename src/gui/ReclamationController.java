/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.ServiceReclamation;

/**
 *
 * @author RYM BACCOURI
 */
public class ReclamationController implements Initializable {
    
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
    
    
    ServiceReclamation fn = new ServiceReclamation();
    Reclamation rec = new Reclamation();
    
    @FXML
    private void ajouterreclamation() throws IOException{ 
    String mail= tfemail.getText();
    String type=tftype.getValue().toString();
    String message=tfmessage.getText();
    String date=tfdate.getText();
    
    if (mail.isEmpty()  || type.isEmpty() || message.isEmpty()|| date.isEmpty() ) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        }
    else{
    
    rec.setEmail(mail);
       rec.setMessage(message);
       rec.setType(type);
       rec.setDate(date);
       try{
      fn.createReclamation(rec);
    
        JOptionPane.showMessageDialog(null, "Reclamation ajouter");
    FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("listerec.fxml"));
        Parent root = loader.load(); 
         tfemail.getScene().setRoot(root);
    
    }catch (IOException ex) {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    
    
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tftype.getItems().removeAll(tftype.getItems());
        tftype.getItems().addAll("Food", "Coach","Exercice");

    }
      
    
    
/*
 private void modifierreclamation(){ 
    String id= tfid.getText();
    String mail= tfemail.getText();
    String type=tftype.getValue().toString();
    String message=tfmessage.getText();
    String date=tfdate.getText();
    if (mail.isEmpty()  || type.isEmpty() || message.isEmpty()|| date.isEmpty() ) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        }
    else{
    
    
    rec.setEmail(mail);
       rec.setMessage(message);
       rec.setType(type);
       rec.setDate(date);
        fn.editReclamation(rec);
       
      
    
    }
    
    
    
    }*/}

    

    

