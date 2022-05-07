/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Event;
import interfacee.userInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import service.userservice;
import util.session;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class MainMenuController implements Initializable {

    @FXML
    private ImageView UserProfilePicutre;
    @FXML
    private Label UserFirstName;
    public final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    public final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    public HBox selectedMenuItem = null;
    @FXML
    public AnchorPane menuPane;

    public AnchorPane getMenuPane() {
        return menuPane;
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
            System.out.println(e.getMessage() + "++");
        }
    }

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadEventDetails(String page, int idEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Pane newLoadedPane = loader.load();

        DetailsController detailsController = loader.getController();
        detailsController.setData(idEvent);

        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadUpdateEventView(String page, Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        AnchorPane newLoadedPane = loader.load();

        UpdateEventController detailsController = loader.getController();
        detailsController.initData(event);

        menuPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void viewProfile(MouseEvent event) {
        try {
            loadFxml("profile11.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @FXML
//    private void test (MouseEvent event) {
//        try {
//            loadFxml("changepassword.fxml");
//        } catch (IOException ex) {
//            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userInterface fn = new userservice();
        this.showProfile();

    }

}
