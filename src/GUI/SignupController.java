/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.user;
import interfacee.userInterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class SignupController implements Initializable {

    private Stage stage;
    private Scene scene;
    private static String CurrentProfilePicture;

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
    private Hyperlink linktoLogin;

    @FXML

    public String uploadpic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open");
        File image = fileChooser.showOpenDialog(Calometre.primaryStage);
        if (image != null) {
            int index = (image.toString().lastIndexOf("."));
            if (index > 0) {
                String extension = image.toString().substring(index + 1);
                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))) {
                    System.err.println("wrong format");
                } else {
                    Random random = new Random();
                    int imageName = random.nextInt(30000000);
                    String pathname = "C:\\Users\\Souhail\\Documents\\images\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    try {
                        SignupController.CurrentProfilePicture = imageName + String.valueOf(imageName) + "." + extension;
                        Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                }
            }
        }
        return SignupController.CurrentProfilePicture;
    }

    userInterface fn = new userservice();
    user test = new user();

    @FXML

    private void signupuser() throws IOException {
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

            System.out.println(CurrentProfilePicture);

            test.setEmail(mail);
            test.setFirstname(fname);
            test.setLastname(lname);
            test.setPassword(pass);
            test.setCountry_code(country_code);
            test.setPhonenumber(number);
            test.setRoles(role);
            test.setProfile_picture(CurrentProfilePicture);
            fn.createuser(test);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleCombo.getItems().removeAll(roleCombo.getItems());
        roleCombo.getItems().addAll("Client", "Coach");

    }
}
