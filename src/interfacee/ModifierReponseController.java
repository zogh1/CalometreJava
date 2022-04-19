/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import entity.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.ServiceReclamation;
import service.ServiceReponse;
import util.connexion;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ModifierReponseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_reponse;
    @FXML
    private TextField tf_repondre;
    @FXML
    private ComboBox<Integer> combo_id;
    ServiceReponse sr = new ServiceReponse();
    Reponse rep = new Reponse();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select id from reponse";
            PreparedStatement pst = connexion.getInstance().getCnx()
                    .prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {

                list.add(rs.getInt("id"));

            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierReponse(ActionEvent event) {
        try {

            sr.editReponse(new Reponse(combo_id.getSelectionModel().getSelectedItem(), tf_date.getText(), Integer.parseInt(tf_repondre.getText()), tf_reponse.getText()));
            JOptionPane.showMessageDialog(null, "Reponse modifié");

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("reponse.fxml"));
            Parent root = loader.load();
            tf_date.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getId(MouseEvent event) {
        combo_id.setOnAction(e -> {
            String req = "select date,repondre_id,reponse from reponse where id=?";
            try {
                PreparedStatement pst = connexion.getInstance().getCnx()
                        .prepareStatement(req);
                pst.setInt(1, (Integer) combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    tf_date.setText(rs.getString("date"));
                    tf_repondre.setText(rs.getString("repondre_id"));
                    tf_reponse.setText(rs.getString("reponse"));

                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierReponseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void ajouterReponse(ActionEvent event) {
    }

}
