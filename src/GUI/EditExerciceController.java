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
public class EditExerciceController implements Initializable {

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
                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")|| extension.equals("mp4"))) {
                    System.err.println("wrong format");
                } else {
                  
                    String pathname = "C:\\Users\\yassine\\Desktop\\java\\yassin\\src\\GUI\\videotest\\" + image.getName();
                    File file1 = new File(pathname);
                    EditExerciceController.Picture = image.getName();
                    Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(image.getName());
                }
            }
        }
        return EditExerciceController.Picture;
    }

    private void refreshtables() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackExerciceView.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public exercice itemSelected(exercice y) {

        ex.setId(y.getId());
        ex.setNom(y.getNom());
        ex.setObjectif(y.getObjectif());
        ex.setDescription(y.getDescription());
        ex.setNomtype_id(y.getNomtype_id());
        ex.setVideo(y.getVideo());
        nameEx.setText(ex.getNom());
        descEx.setText(ex.getDescription());
        objEx.setText(ex.getObjectif());
       
        typeEx.setValue(ex.getNomtype_id().getNom());
        Picture = ex.getVideo();

        return ex;
    }

    public void editEx() throws java.io.IOException {

        String selected = typeEx.getValue().toString();
        typeExercice typeEx = fn1.findByName(selected);
        String name = nameEx.getText();
        String objectif = objEx.getText();
        String description = descEx.getText();
        
        int x = Integer.parseInt(typeEx.getNom());
        
        
        int id = ex.getId();
        ex.setId(id);
        ex.setNom(name);
        ex.setObjectif(objectif);
        ex.setDescription(description);
        
        ex.setVideo(Picture);
        
        tex.setId(x);
        fn.editExercice(ex,tex);
        Stage stage = (Stage) closewindow.getScene().getWindow();
        stage.close();
        this.refreshtables();
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
