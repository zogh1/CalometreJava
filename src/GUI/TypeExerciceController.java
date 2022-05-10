/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.typeExercice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.typeExerciceService;

/**
 *
 * @author Yassine
 */
public class TypeExerciceController implements Initializable {

    typeExerciceService fn = new typeExerciceService();
    typeExercice test = new typeExercice();

    @FXML
    private TextField typeExerciceField;

    public void addtype() {

        String nom = typeExerciceField.getText();

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            test.setNom(nom);
            fn.addType(test);
        }
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {

    }

}
