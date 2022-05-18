/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.Event;
import entity.Recette;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private VBox vBoxMenu;

    private final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    private final Border border = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));

    private Label selectedMenuItem = null;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private Label userName;
    @FXML
    private ImageView UserImage;
    private userservice userservice;

    public AnchorPane getMenuPane() {
        return menuPane;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userservice = new userservice();

        Calometre.mainController = this;
        userName.setText(session.getUser().getFirstname() + " " + session.getUser().getLastname());

        Image i;
        try {
            i = new Image(new FileInputStream("C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\profilePicture\\" + session.getUser().getProfile_picture()));
            UserImage.setImage(i);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectMenueItem(MouseEvent event) {
        Label hb = (Label) event.getSource();

        if (!(hb).equals(selectedMenuItem)) {
            if (selectedMenuItem != null) {
                selectedMenuItem.setBackground(unfocusBackground);
            }

            selectedMenuItem = hb;
            selectedMenuItem.setBackground(focusBackground);
        }

        try {
            switch (selectedMenuItem.getText().trim()) {
                case "Events":
                    loadFxml("EventsView.fxml");
                    break;
                case "Exercice":
                    loadFxml("FrontExerciceView.fxml");
                    break;
                case "Complaint":
                    loadFxml("listerec.fxml");
                    break;
                case "Products":
                    loadFxml("market.fxml");
                    break;
                case "Cart":
                    loadFxml("EventsView.fxml");
                    break;
                case "Recette":
                    loadFxml("RecettesView.fxml");
                    break;
                case "IMC":
                    loadFxml("CalculeIMCForm.fxml");
                    break;
                case "Log out":
                    logout();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().clear();
        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadEventDetails(String page, int idEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        Pane newLoadedPane = loader.load();

        DetailsController detailsController = loader.getController();
        detailsController.setData(idEvent);
        menuPane.getChildren().clear();

        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadUpdateEventView(String page, Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        AnchorPane newLoadedPane = loader.load();

        UpdateEventController detailsController = loader.getController();
        detailsController.initData(event);
        menuPane.getChildren().clear();

        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadUpdateRecetteView(String page, Recette recette) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        AnchorPane newLoadedPane = loader.load();

        UpdateRecetteController detailsController = loader.getController();
        detailsController.initData(recette);

        menuPane.getChildren().add(newLoadedPane);
    }

    public void loadProfilePic() {
        Image i;
        try {
            i = new Image(new FileInputStream("C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\profilePicture\\" + session.getUser().getProfile_picture()));
            UserImage.setImage(i);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewProfile(MouseEvent event) {
        try {
            loadFxml("profile.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
        userservice.logout();

    }
}
