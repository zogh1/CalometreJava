/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

    private HBox selectedMenuItem = null;
    @FXML
    private AnchorPane menuPane;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void selectMenueItem(MouseEvent event) {
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

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void viewProfile(MouseEvent event) {
        try {
            loadFxml("profile.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
