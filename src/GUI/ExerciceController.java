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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import service.exerciceService;
import service.typeExerciceService;

/**
 *
 * @author Yassine
 */
public class ExerciceController implements Initializable {

    exerciceService fn = new exerciceService();
    exercice test = new exercice();

    typeExerciceService fn1 = new typeExerciceService();
    typeExercice test1 = new typeExercice();
    private static String ExerciceVideo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField objectifField;
    @FXML
    private Button addvideoButton;
    @FXML
    private ComboBox typeCombo;

    @FXML

    public String uploadvid() {
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
                    Random random = new Random();
                    int imageName = random.nextInt(30000000);
                    String pathname = "C:\\Users\\Souhail\\Documents\\images\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    try {
                        ExerciceController.ExerciceVideo = imageName + String.valueOf(imageName) + "." + extension;
                        Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                }
            }
        }
        return ExerciceController.ExerciceVideo;
    }

    public void addExercice() {
        String type = typeCombo.getValue().toString();
        String nom = nameField.getText();
        String description = descriptionField.getText();
        String objectif = objectifField.getText();

        String selected = typeCombo.getValue().toString();

        int id = Integer.parseInt(selected);

        if (nom.isEmpty() || description.isEmpty() || objectif.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            test.setNom(nom);
            test.setObjectif(objectif);
            test.setDescription(description);

            test.setVideo(ExerciceVideo);
            test1.setId(id);
            fn.addExercice(test1, test);
        }
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        List<typeExercice> listExc = fn1.readType();
        for (typeExercice list : listExc) {
            int id = list.getId();
            typeCombo.getItems().add(id);
        }

    }

}
