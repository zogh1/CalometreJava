/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.category;
import entity.user;
import interfacee.userInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.categoryservice;


/**
 *
 * @author Souhail
 */
public class CategoryController implements Initializable {
    

    @FXML
    private TextField nameField;
    private ListView catview;
    

    categoryservice fn = new categoryservice();
    category cat = new category();

    @FXML
    private void addcategory() {
        String name = nameField.getText();
       
        if (name.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            cat.setName(name);
           if ( fn.createcategory(cat)){
               Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("null");
            alert.setContentText("productaddedsuccessfully");
            alert.showAndWait();
               
           }
        }
    }
@FXML
    private void showcat() {
        
       
       
            
           fn.getallcategory();
              
               
           
        }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
