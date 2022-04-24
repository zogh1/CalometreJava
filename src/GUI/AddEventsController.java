/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import service.ServiceEvent;

/**
 *
 * @author wassim
 */
public class AddEventsController {

    private Stage stage;
    private ServiceEvent service = new ServiceEvent();
    @FXML
    private JFXButton btnajouter;
    @FXML
    private JFXDatePicker txtdatedebut;
    @FXML
    private JFXDatePicker textdatefin;
    @FXML
    private JFXTextArea txtdescrip;
    private JFXTextField txtnom;

    @FXML
    private ImageView img;
    private JFXTextField txtnombre_participants;
    private JFXTextField txtloaction;

    File file;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtlocation;
    @FXML
    private JFXTextField txtnbrplace;

    @FXML
    private void addimage(ActionEvent event) {
        Path to11 = null;
        String m = null;
        String path = "C:\\Users\\wassim\\Desktop\\Pidev3A\\Calometre\\public\\uploads\\Event_images";
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

    @FXML
    private void addEvent(ActionEvent event) throws FileNotFoundException, IOException {

        Stage current = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (event.getSource() == btnajouter) {

            FileInputStream fl = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            fl.read(data);
            fl.close();
            Event event1 = new Event(txtname.getText(), txtdatedebut.getValue().toString(), textdatefin.getValue().toString(), txtdescrip.getText(), Integer.parseInt(txtnbrplace.getText()), txtlocation.getText(), fileName);
            service.createEvent(event1);

            goToEventsView();
        }
    }

    private void goToEventsView() throws IOException {
        calometre.Calometre.mainController.getMenuPane().getChildren().clear();
        calometre.Calometre.mainController.loadFxml("EventsView.fxml");
    }

}
