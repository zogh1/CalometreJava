/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Event;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class UpdateEventController implements Initializable {

    @FXML
    private JFXButton btnajouter;
    @FXML
    private JFXDatePicker txtdatedebut;
    @FXML
    private JFXDatePicker textdatefin;
    @FXML
    private JFXTextArea txtdescrip;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtlocation;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextField txtnbrplace;
    
    File file;
    
    private Stage stage;

    
    ServiceEvent serviceEvent ;
    @FXML
    private Label idNotVisible;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public LocalDate convertDateStringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
  

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    
    void initData(Event e){
        txtname.setText(e.getNom());
        txtdatedebut.setValue(convertDateStringToLocalDate(e.getDate_debut()));
        textdatefin.setValue(convertDateStringToLocalDate(e.getDate_fin()));
        txtlocation.setText(e.getLieu());
        txtnbrplace.setText(String.valueOf(e.getNombre_participants()));
        txtdescrip.setText(e.getDescription());
        idNotVisible.setText(String.valueOf(e.getId()));
    
    }
    
    


  

    

    @FXML
    private void updateEvent(ActionEvent event) throws FileNotFoundException, IOException {
        serviceEvent = new ServiceEvent();
        Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();

        if (event.getSource() == btnajouter) {
           
          
                FileInputStream fl = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                String fileName = file.getName();
                fl.read(data);
                fl.close();
                Event event1 = new Event(txtname.getText(),txtdatedebut.getValue().toString(),textdatefin.getValue().toString(),txtdescrip.getText(),Integer.parseInt(txtnbrplace.getText()),txtlocation.getText(), fileName);
                serviceEvent.updateEvent(event1,Integer.parseInt(idNotVisible.getText()));
                Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                current.close();
                stage.show();
           
        }
        
    }

    @FXML
    private void selectImage(ActionEvent event) {
        
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
    
}
