/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Recette;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import service.RecetteService;

/**
 *
 * @author Ahmed Mahjoub
 */
public class AddRecetteController implements Initializable {

    private Stage stage;
    private RecetteService service = new RecetteService();

    File file;
    @FXML
    private JFXTextField txtname;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton btnajouter;
    @FXML
    private ComboBox<String> CB_Regime;
    @FXML
    private ComboBox<String> CB_Category;

    @FXML
    private void addRecette(ActionEvent event) throws FileNotFoundException, IOException {

        Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (event.getSource() == btnajouter) {

            FileInputStream fl = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            fl.read(data);
            fl.close();
            System.out.println("======"+CB_Regime.getValue());
            Recette recette = new Recette(txtname.getText(), CB_Regime.getValue(), CB_Category.getValue(), fileName);
            service.addRecette(recette);

            goToRecettesView();

        }
    }

    @FXML
    private void addimage(ActionEvent event) {
        Path to11 = null;
        String m = null;
        String path = "C:\\Users\\wassim\\Documents\\NetBeansProjects\\Calometre\\src\\img";
        JFileChooser chooser1 = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser1.setFileFilter(filter);
        int returnVal = chooser1.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser1.getSelectedFile().getAbsolutePath();

            file = chooser1.getSelectedFile();
            String fileName = file.getName();
            if (chooser1.getSelectedFile() != null) {

                try {

                    Path from1 = Paths.get(chooser1.getSelectedFile().toURI());
                    to11 = Paths.get(path + "\\" + fileName);

                    CopyOption[] options1 = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };

                    Files.copy(from1, to11, options1);
                    //  System.out.println(file);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }

    private void goToRecettesView() throws IOException {
        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("RecettesView.fxml");
    }

    private boolean validateName(TextField name) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(name.getText());
        if ((name.getText().length() == 0)
                || (m.find() && m.group().equals(name.getText()))) {
            name.setEffect(null);
            return true;
        } else {
            new animatefx.animation.Shake(name).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            name.setEffect(in);

            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> l = FXCollections.observableArrayList("Hyperprotéiné", "Protéiné", "Dissocié", "Hypocalorique", "Végétarien", "Anticellulite",
                "Sans sel", "Hypoglucidique");
        CB_Regime.setItems(l);
        ObservableList<String> lC = FXCollections.observableArrayList("Sucrée", "Salé", "Acide","Amer", "Umami");
                
        CB_Category.setItems(lC);
        CB_Regime.getSelectionModel().select("Sans sel");
          CB_Category.getSelectionModel().select("Sucrée");
    }
}
