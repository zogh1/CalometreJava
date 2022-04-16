/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.user;
import interfacee.userInterface;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private static String currentMailReset;

    public void switchToverifyresetCode(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("verifyresetcode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void LinkToResetPassword() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resetpassword.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    public void LinkToCreateAnAccount() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField resetcodeField;
    @FXML
    private PasswordField newpasswordField;
    @FXML
    private PasswordField confirmpasswordField;
    @FXML
    private Hyperlink ForgotPassword;
    @FXML
    private Hyperlink CreateAnAccount;

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

    @FXML
    private void sendresetCode() throws IOException, Exception {
        LoginController.currentMailReset = emailField.getText();

        if (emailField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please enter your email");
            alert.showAndWait();
        } else {
            if (fn.sendresetCode(LoginController.currentMailReset)) {
                Parent root = FXMLLoader.load(getClass().getResource("verifyresetcode.fxml"));
                Calometre.primaryStage.setScene(new Scene(root));
                Calometre.primaryStage.show();
            }

        }

    }

    @FXML
    private void verifyresetCode() throws IOException, Exception {
        String resetCode = resetcodeField.getText();
        int code = Integer.parseInt(resetCode);
        if (resetCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please enter reset code");
            alert.showAndWait();
        } else if (code != userservice.code) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please verify reset code");
            alert.showAndWait();
        } else {
            if (code == userservice.code) {
                Parent root = FXMLLoader.load(getClass().getResource("resetpassword.fxml"));
                Calometre.primaryStage.setScene(new Scene(root));
                Calometre.primaryStage.show();
            }

        }

    }

    @FXML
    public void resetpassword() throws IOException, Exception {
        String npass = newpasswordField.getText();
        String cpass = confirmpasswordField.getText();

        if (npass.isEmpty() || cpass.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else if (!npass.equals(cpass)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("password don't match");
            alert.showAndWait();

        } else {

            if (fn.resetPassword(LoginController.currentMailReset, npass)) {
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Calometre.primaryStage.setScene(new Scene(root));
                Calometre.primaryStage.show();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
