/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.user;
import interfacee.userInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class LoginController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    userInterface fn = new userservice();
    user test = new user();

    @FXML
    private void loginuser() {
        String mail = emailField.getText();
        String pass = passwordField.getText();
        if (mail.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            test.setEmail(mail);
            test.setPassword(pass);
            fn.login(test);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
