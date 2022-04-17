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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class SignupController implements Initializable {

    private Stage stage;
    private Scene scene;

    public void linkToLoginPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }
    @FXML
    private TextField emailField;
    @FXML
    private TextField fnameField;
    @FXML
    private TextField lnameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField ccField;
    @FXML
    private TextField pnField;
    @FXML
    private ComboBox roleCombo;
    @FXML
    private Button profilepic;
    @FXML
    private Hyperlink linktoLogin;

    userInterface fn = new userservice();
    user test = new user();

    @FXML

    private void signupuser() {
        String mail = emailField.getText();
        String pass = passwordField.getText();
        String fname = fnameField.getText();
        String lname = lnameField.getText();
        String country_code = ccField.getText();
        String phone_number = pnField.getText();

        if (mail.isEmpty() || pass.isEmpty() || fname.isEmpty() || lname.isEmpty() || roleCombo.getValue() == null || phone_number.isEmpty() || country_code.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else if (passwordField.getText().length() < 8) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("votre mdp doit contenir au moins 8 characteres");
            alert.showAndWait();
        } else if (!(emailField.getText().matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("verifier votre email");
            alert.showAndWait();

        } else {
            String role = roleCombo.getValue().toString();
            int number = Integer.parseInt(phone_number);
            test.setEmail(mail);
            test.setFirstname(fname);
            test.setLastname(lname);
            test.setPassword(pass);
            test.setCountry_code(country_code);
            test.setPhonenumber(number);
            test.setRoles(role);
            test.setProfile_picture("121");
            fn.createuser(test);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleCombo.getItems().removeAll(roleCombo.getItems());
        roleCombo.getItems().addAll("Client", "Coach");

    }
}
