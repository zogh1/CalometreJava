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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private VBox vBoxMenu;
    @FXML
    private ImageView UserProfilePicutre;
    @FXML
    private Label UserFirstName;

    public void LinkToSettings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    public void LinkToConditions() throws IOException {
        try {
            Calometre.mainController.loadFxml("conditions.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void LinkToChangePassword() throws IOException {
        try {
            Calometre.mainController.loadFxml("changepassword.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LinkToProfile() throws IOException {
        try {
            Calometre.mainController.loadFxml("profile.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void changepassword() throws IOException {
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

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("mot de passe a été changée");
            alert.showAndWait();

            fn.updatePassword(opass, npass);

            fn.logout();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Calometre.primaryStage.setScene(new Scene(root));
            Calometre.primaryStage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
