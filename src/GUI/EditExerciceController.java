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
public class EditExerciceController implements Initializable {

    exerciceService fn = new exerciceService();
    exercice test = new exercice();

    typeExerciceService fn1 = new typeExerciceService();
    typeExercice test1 = new typeExercice();
    private static String ExerciceVideo;
    @FXML
    private TextField newnameField;
    @FXML
    private TextField newdescriptionField;

    @FXML
    private TextField newobjectifField;
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

    public void UpdateInfo() throws IOException {

        String type = typeCombo.getValue().toString();
        String nom = newnameField.getText();
        String description = newdescriptionField.getText();
        String objectif = newobjectifField.getText();

        String selected = typeCombo.getValue().toString();

        int id = Integer.parseInt(selected);

        test.setNom(nom);
        test.setObjectif(objectif);
        test.setDescription(description);

        test.setVideo(ExerciceVideo);
        test.setId(id);
        fn.editExercice(test);

    }

    ;


    @Override

    public void initialize(URL location, ResourceBundle resources) {
        List<typeExercice> listExc = fn1.readType();
        for (typeExercice list : listExc) {
            int id = list.getId();
            typeCombo.getItems().add(id);

        }

    }

}
