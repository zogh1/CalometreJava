/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.typeExercice;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.typeExerciceService;

/**
 *
 * @author Souhail
 */
public class TypeExerciceController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    TableView<typeExercice> typeexerciceview;
    @FXML
    private TableColumn<typeExercice, String> TypeId;
    @FXML
    private TableColumn<typeExercice, String> TypeExerciceName;
    ObservableList<typeExercice> oblist = FXCollections.observableArrayList();

    typeExerciceService fn = new typeExerciceService();
    typeExercice tex = new typeExercice();
    

    public void Edittypeexercice() throws IOException {
        String name = nameField.getText();
        tex = typeexerciceview.getSelectionModel().getSelectedItem();
        tex.setId(tex.getId());
        tex.setNom(name);

        fn.editType(tex);
        this.refreshtables();
    }

    public void deletetypeexercice() throws IOException {

        tex = typeexerciceview.getSelectionModel().getSelectedItem();
        
        fn.deleteType(tex.getId());

         this.refreshtables();
    }
private void refreshtables() throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("TypeExercice.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
}
    private void typeView() {

        // TODO
        List<typeExercice> li = fn.readType();

        li.forEach(e -> {
            oblist.add(e);
            TypeId.setCellValueFactory(new PropertyValueFactory<>("id"));
            TypeExerciceName.setCellValueFactory(new PropertyValueFactory<>("nom"));

        });

        typeexerciceview.setItems(oblist);

    }

   
    @FXML
    private void addtypeexercice() throws IOException {
        String name = nameField.getText();
        tex.setNom(name);
        fn.addType(tex);
        this.refreshtables();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.typeView();
    }
}
