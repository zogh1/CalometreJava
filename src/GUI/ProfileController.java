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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import service.userservice;
import util.session;

/**
 *
 * @author Souhail
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView UserProfilePicutre;
    @FXML
    private ImageView UserProfilePicutre1;

    @FXML
    private Label UserFirstName;

    public final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    public final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    public HBox selectedMenuItem = null;
    @FXML
    public AnchorPane menuPane;

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().add(newLoadedPane);
    }

    public void LinkToChangePassword() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    @FXML
    public void selectMenueItem(MouseEvent event) {
        HBox hb = (HBox) event.getSource();

        if (!(hb).equals(selectedMenuItem)) {
            if (selectedMenuItem != null) {
                selectedMenuItem.setBackground(unfocusBackground);
            }

            selectedMenuItem = hb;
            selectedMenuItem.setBackground(focusBackground);
        }
        try {
            loadFxml("EventsView.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void LinkToSettings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    public void showProfile() {
        String firstName = session.getUser().getFirstname();
        try {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\Souhail\\Documents\\images\\" + session.getUser().getProfile_picture());
            UserProfilePicutre.setImage(new Image(inputstream));
        } catch (FileNotFoundException ex) {
        }
        UserFirstName.setText(firstName);

    }

    public void showProfileOnTop() {

        try {
            FileInputStream inputstream = new FileInputStream("C:\\Users\\Souhail\\Documents\\images\\" + session.getUser().getProfile_picture());
            UserProfilePicutre1.setImage(new Image(inputstream));
        } catch (FileNotFoundException ex) {
        }

    }
    userInterface fn = new userservice();
    user test = new user();

    public void logout() throws IOException {
        fn.logout();

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

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
                    String pathname = "C:\\Users\\Souhail\\Documents\\images\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    try {
                        String newImage = imageName + String.valueOf(imageName) + "." + extension;
                        Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        session.getUser().setProfile_picture(newImage);
                        fn.updateProfilePicture(session.getUser());
                    } catch (IOException e) {
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                    Calometre.primaryStage.setScene(new Scene(root));
                    Calometre.primaryStage.show();
                }
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showProfile();
        this.showProfileOnTop();

    }

}
