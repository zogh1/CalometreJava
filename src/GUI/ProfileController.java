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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.userservice;
import util.session;

/**
 *
 * @author Souhail
 */
public class ProfileController implements Initializable {

    @FXML
    public ImageView UserProfilePicutre;
    @FXML
    public ImageView UserProfilePicutre1;

    @FXML
    public Label UserFirstName;
    @FXML
    private TextField UserEmail;
    @FXML
    private TextField UserFirstName1;
    @FXML
    private TextField UserLastName;
    @FXML
    private TextField UserPhoneNumber;

    public void LinkToChangePassword() throws IOException {
        try {
            Calometre.mainController.loadFxml("changepassword.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LinkToSettings() throws IOException {
        try {
            Calometre.mainController.loadFxml("settings.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void LinkToConditions() throws IOException {
        try {
            Calometre.mainController.loadFxml("conditions.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showProfile() {
        String firstName = session.getUser().getFirstname();
        try {
            FileInputStream inputstream = new FileInputStream("C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\profilePicture\\" + session.getUser().getProfile_picture());
            System.out.println("C:\\Users\\wassim\\Desktop\\Pidev3A\\Calometre\\public\\uploads\\profilePicture\\" + session.getUser().getProfile_picture());
            UserProfilePicutre.setImage(new Image(inputstream));
        } catch (FileNotFoundException ex) {

        }
        UserFirstName1.setText(firstName);

    }

    userInterface fn = new userservice();
    user test = new user();

    public void logout() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
        fn.logout();

    }

    public void UploadProfilePicture() throws IOException {

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
                    String pathname = "C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\profilePicture\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    try {
                        String newImage = imageName + String.valueOf(imageName) + "." + extension;
                        Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        session.getUser().setProfile_picture(newImage);
                        fn.updateProfilePicture(session.getUser());
                    } catch (IOException e) {
                    }

                    calometre.Calometre.mainController.getMenuPane().getChildren().clear();

                    calometre.Calometre.mainController.loadFxml("profile.fxml");
                    calometre.Calometre.mainController.loadProfilePic();

                }
            }

        }

    }

    public void DeleteAccount() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer votre compte ?");

        ButtonType buttonTypeOne = new ButtonType("Oui");

        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            fn.deleteuser(session.getUser().getId());
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Calometre.primaryStage.setScene(new Scene(root));
            Calometre.primaryStage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    public void UpdateInfo() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText("Êtes-vous sûr de changer vos information? ");

        ButtonType buttonTypeOne = new ButtonType("Oui");

        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            String phone_number = UserPhoneNumber.getText();
            int number = Integer.parseInt(phone_number);
            String mail = UserEmail.getText();

            String fname = UserFirstName1.getText();
            String lname = UserLastName.getText();

            test.setEmail(mail);
            test.setFirstname(fname);
            test.setLastname(lname);
            test.setPhonenumber(number);
            fn.updateuserinfo(test);
            Parent root = FXMLLoader.load(getClass().getResource("setings.fxml"));
            Calometre.primaryStage.setScene(new Scene(root));
            Calometre.primaryStage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showProfile();
        String FirstName = session.getUser().getFirstname();
        String Email = session.getUser().getEmail();
        String LastName = session.getUser().getLastname();
        int PhoneNumber = session.getUser().getPhonenumber();

        String PN = String.valueOf(PhoneNumber);
        UserEmail.setText(Email);

        UserFirstName1.setText(FirstName);
        UserLastName.setText(LastName);

        UserPhoneNumber.setText(PN);
    }

}
