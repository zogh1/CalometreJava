/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.rest.preview.understand.assistant.task.Sample;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import service.ServiceReclamation;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import util.connexion;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ListerecController implements Initializable {

    //  @FXML
    // private ListView<Reclamation> listRec;
    @FXML
    private TextField tf_recherche;

    private final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    private final Border border = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));

    private HBox selectedMenuItem = null;
    private AnchorPane menuPane;
    @FXML
    private TableView<Reclamation> tabreclamation;

    @FXML
    private TableColumn<Reclamation, String> tfemail;
    @FXML
    private TableColumn<Reclamation, String> tfdate;
    @FXML
    private TableColumn<Reclamation, String> tftype;
    @FXML
    private TableColumn<Reclamation, String> tfmessage;
    @FXML
    private Button btnm;
    @FXML
    private TableColumn<Reclamation, String> tfaction;

    private final int sizeUser = 5;

    private static int startUser = 0;
    @FXML
    private Button nextPage;
    @FXML
    private Button previousPage;

    /**
     * Initializes the controller class.
     */
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

    private void viewProfile(MouseEvent event) {
        try {
            loadFxml("profile.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loadReclamations(this.sizeUser, ListerecController.startUser);

    }

    @FXML
    private void ajoutRec(MouseEvent event) {
        try {
            calometre.Calometre.mainController.getMenuPane().getChildren().clear();
            calometre.Calometre.mainController.loadFxml("EventsView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ListerecController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void nextUserPage() throws IOException {

        ListerecController.startUser = ListerecController.startUser + this.sizeUser;
        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("listerec.fxml");
        nextPage.setDisable(false);

    }

    @FXML
    public void previousUserPage() throws IOException {

        ListerecController.startUser = ListerecController.startUser - this.sizeUser;
        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("listerec.fxml");
        previousPage.setDisable(false);

    }

    private void supprimerRec(ActionEvent event) {

        Reclamation R = new Reclamation();
        R = tabreclamation.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une reclamation à supprimer");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer la reclamation?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ServiceReclamation SR = new ServiceReclamation();
                SR.deleteReclamation(R.getId());
                JOptionPane.showMessageDialog(null, "Reclamation supprimé");
                loadReclamations(this.sizeUser, ListerecController.startUser);
            }
        }
    }

    Reclamation selectedReponse = null;

    public void loadReclamations(int i, int j) {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> lr = sr.pagination(i, j);
        ObservableList<Reclamation> data = FXCollections.observableArrayList(lr);
        tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tftype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tfmessage.setCellValueFactory(new PropertyValueFactory<>("message"));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellDelete = (TableColumn<Reclamation, String> param) -> {
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button editButton = new Button();
                        editButton.setText("Supp");
                        editButton.setStyle("-fx-font-size:14;-fx-text-fill:white;-fx-background-color:#ff0000; -fx-background-radius:20px;-fx-border-radius:20px");
                        editButton.setPrefSize(65, 50);

                        editButton.setOnMouseClicked((MouseEvent event) -> {
                            selectedReponse = tabreclamation.getSelectionModel().getSelectedItem();
                            if (selectedReponse == null) {
                            } else {
                                try {
                                    sr.deleteReclamation(selectedReponse.getId());
                                    calometre.Calometre.mainController.getMenuPane().getChildren().clear();
                                    calometre.Calometre.mainController.loadFxml("listerec.fxml");
                                } catch (IOException ex) {
                                }
                            }
                        });

                        HBox managebtn = new HBox(editButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editButton, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        tfaction.setCellFactory(cellDelete);
        tabreclamation.setItems(data);

    }

    @FXML
    private void stats(MouseEvent event) throws ParseException {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXBarChart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistiques reclamation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Window primaryStage = null;
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            gui.FXBarChartController controller = loader.getController();
            ServiceReclamation sr = new ServiceReclamation();
            List<Reclamation> lr = sr.readReclamation();
            ObservableList<Reclamation> data = FXCollections.observableArrayList(lr);

            tabreclamation.setItems(data);
            controller.setReclamationData(data);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modifierRec(ActionEvent event) {

        try {
           calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("ModifierReclamation.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ListerecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
