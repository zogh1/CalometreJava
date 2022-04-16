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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class SignupController implements Initializable {

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
        String role = roleCombo.getValue().toString();
        int number = Integer.parseInt(phone_number);

        if (mail.isEmpty() || pass.isEmpty() || fname.isEmpty() || lname.isEmpty() || country_code.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
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
