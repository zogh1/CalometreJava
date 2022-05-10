/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.user;
import interfacee.userInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import service.userservice;
import util.session;

/**
 *
 * @author Souhail
 */
public class SettingsController implements Initializable {

    @FXML
    private VBox vBoxMenu;
    @FXML
    private ImageView UserProfilePicutre;
    @FXML
    private Label UserFirstName;
    @FXML
    private Button DeleteAccountButton;

    private final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    private final Border border = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));

    private HBox selectedMenuItem = null;
    @FXML
    private AnchorPane menuPane;

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().add(newLoadedPane);
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

    public void LinkToChangePassword() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    public void LinkToConditions() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("conditions.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    public void LinkToProfile() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }
    @FXML
    private TextField UserEmail;
    @FXML
    private TextField UserFirstName1;
    @FXML
    private TextField UserLastName;
    @FXML
    private TextField UserPhoneNumber;

    public void DisplayInfo() {

    }
    userInterface fn = new userservice();
    user test = new user();

    public void DeleteAccount() throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer votre compte ?");

        ButtonType buttonTypeOne = new ButtonType("Oui");

        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonData.CANCEL_CLOSE);

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
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setHeaderText("Êtes-vous sûr de changer vos information? ");

        ButtonType buttonTypeOne = new ButtonType("Oui");

        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonData.CANCEL_CLOSE);

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
