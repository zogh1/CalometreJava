/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.category;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.categoryservice;

/**
 *
 * @author Souhail
 */
public class CategoryController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    TableView<category> catview;
    @FXML
    private TableColumn<category, String> catID;
    @FXML
    private TableColumn<category, String> catName;
    ObservableList<category> oblist = FXCollections.observableArrayList();

    categoryservice fn = new categoryservice();
    category cat = new category();
    category c = null;

    public void edit() throws IOException {
        String name = nameField.getText();
        c = catview.getSelectionModel().getSelectedItem();
        c.setId(c.getId());
        c.setName(name);

        fn.updatecategory(c);
        this.refreshtables();
    }

    public void deleteCat() throws IOException {

        c = catview.getSelectionModel().getSelectedItem();
        c.setId(c.getId());
        fn.deletecategory(c.getId());

        this.refreshtables();
    }

    private void refreshtables() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    private void catview() {

        // TODO
        List<category> li = fn.getallcategory();

        li.forEach(e -> {
            oblist.add(e);
            catID.setCellValueFactory(new PropertyValueFactory<>("id"));
            catName.setCellValueFactory(new PropertyValueFactory<>("name"));

        });

        catview.setItems(oblist);

    }

    //thez lel products
    public void GoToProd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToUserListAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToStatAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userstats.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToStats() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("statProduits.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    @FXML
    private void addcategory() throws IOException {
        String name = nameField.getText();

        if (name.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            cat.setName(name);
            if (fn.createcategory(cat)) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("null");
                alert.setContentText("productaddedsuccessfully");
                alert.showAndWait();

            }
        }
        this.refreshtables();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.catview();
    }
}
