/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ListerecController implements Initializable {

    @FXML
    private ListView<Reclamation> listRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> lr = sr.readReclamation();
        ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
        listRec.setItems(data);
                
    }    

    @FXML
    private void ajoutRec(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("reclamation.fxml"));
            Parent root=loader.load();
            ReclamationController aac=loader.getController();
            listRec.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListerecController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimerRec(ActionEvent event) {
       
        Reclamation R = new Reclamation();
        R = listRec.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une reclamation à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer la reclamation?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ServiceReclamation SR= new ServiceReclamation();
                SR.deleteReclamation(R.getId());
                JOptionPane.showMessageDialog(null, "Reclamation supprimé");
                loadReclamations();
            }
    }
    }
    
    public void loadReclamations() {
        ServiceReclamation SR = new ServiceReclamation();
        ArrayList<Reclamation> listeArticle = (ArrayList<Reclamation>) SR.readReclamation();

        ObservableList observableList = FXCollections.observableArrayList(listeArticle);
        listRec.setItems(observableList);

    }

    @FXML
    private void stats(ActionEvent event) throws ParseException {
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
        ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
    
    listRec.setItems(data);
        controller.setReclamationData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}
