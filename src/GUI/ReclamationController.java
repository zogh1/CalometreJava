/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import entity.user;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.ServiceReclamation;
import service.userservice;
import util.session;

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
    private DatePicker tfdate;
    @FXML
    private ComboBox tftype;
    @FXML
    private TextField tfmessage;

    @FXML
    private Button btnajouter;

    userservice us = new userservice();
    user u = new user();

    ServiceReclamation fn = new ServiceReclamation();
    Reclamation rec = new Reclamation();

    @FXML
    private void ajouterreclamation() throws IOException {

        //if (session.getUser().getRoles().contains("Admin"))
        //{
        // Parent Parent=FXMLLoader.load(getClass().getRessource("/GUI/Listerec.fxml"));
        String mail = tfemail.getText();
        String type = tftype.getValue().toString();
        String message = tfmessage.getText();
        String date = String.valueOf(tfdate.getValue());

        if (mail.isEmpty() || type.isEmpty() || message.isEmpty() || date.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else if (!(tfemail.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("verifier votre email");
            alert.showAndWait();
        } else if (tfmessage.getText().length() < 5 || tfmessage.getText().length() > 30) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("votre message doit etre entre 5 et 30 caratere");
            alert.showAndWait();
        } else {

            rec.setEmail(mail);
            rec.setMessage(message);
            rec.setType(type);
            rec.setDate(date);
            try {
                fn.createReclamation(rec);

                calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("listerec.fxml");

            } catch (IOException ex) {
                Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tftype.getItems().removeAll(tftype.getItems());
        tftype.getItems().addAll("Food", "Coach", "Exercice");

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
    //mtaa affichage win
    
    
    
    }*/
}
