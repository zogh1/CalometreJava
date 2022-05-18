/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.exercice;
import entity.typeExercice;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.exerciceService;
import service.typeExerciceService;

/**
 *
 * @author seifd
 */
public class AddExerciceController implements Initializable {

    static String Picture;
    exerciceService fn = new exerciceService();
    exercice ex = new exercice();

    typeExerciceService fn1 = new typeExerciceService();
    typeExercice tex = new typeExercice();

    @FXML
    private ComboBox typeEx;
    @FXML
    private TextField nameEx;
    @FXML
    private TextField descEx;
    @FXML
    private TextField objEx;
    @FXML
    Button closewindow;

    @FXML
    public String uploadImage() throws java.io.IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open");
        File image = fileChooser.showOpenDialog(Calometre.primaryStage);
        if (image != null) {
            int index = (image.toString().lastIndexOf("."));
            if (index > 0) {
                String extension = image.toString().substring(index + 1);
                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("mp4"))) {
                    System.err.println("wrong format");
                } else {

                    String pathname = "C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\exercices\\" + image.getName();
                    File file1 = new File(pathname);
                    AddExerciceController.Picture = image.getName();
                    Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(image.getName());
                }
            }
        }
        return AddExerciceController.Picture;
    }

    private void refreshtables() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackExerciceView.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void AddExercice() throws java.io.IOException {

        String selected = typeEx.getValue().toString();
        typeExercice typeEx = fn1.findByName(selected);
        String name = nameEx.getText();
        String objectif = objEx.getText();
        String description = descEx.getText();

        int x = Integer.parseInt(typeEx.getNom());

        if (name.isEmpty() || objectif.isEmpty() || description.isEmpty() || selected.isEmpty()) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Veuillez remplir tous les champs obligatoires");
            alert.showAndWait();

        } else if (name.length() > 30) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Le nom doit contenir moins de 30 characters");
            alert.showAndWait();

        } else {

            ex.setNom(name);
            ex.setObjectif(objectif);
            ex.setDescription(description);

            ex.setVideo(Picture);

            tex.setId(x);
            fn.addExercice(tex, ex);
            Stage stage = (Stage) closewindow.getScene().getWindow();
            stage.close();
            this.refreshtables();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<typeExercice> listType = fn1.readType();
        for (typeExercice list : listType) {
            String id = list.getNom();
            typeEx.getItems().add(id);
        }

    }

}
