/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.user;
import interfacee.userInterface;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class ChangePasswordController implements Initializable {

    public void LinkToProfile() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    @FXML
    private PasswordField oldpasswordField;
    @FXML
    private PasswordField newpasswordField;
    @FXML
    private PasswordField confirmpasswordField;

    userInterface fn = new userservice();
    user test = new user();

    @FXML
    private void changepassword() {
        String opass = oldpasswordField.getText();
        String npass = newpasswordField.getText();
        String cpass = confirmpasswordField.getText();

        if (opass.isEmpty() || npass.isEmpty() || cpass.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else if (!npass.equals(cpass)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("password don't match");
            alert.showAndWait();

        } else if (newpasswordField.getText().length() < 8) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("le mdp doit conteir plus que 8 characters");
            alert.showAndWait();

        } else {

            fn.updatePassword(opass, npass);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
