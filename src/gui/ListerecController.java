/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import service.ServiceReclamation;



import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import util.connexion;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ListerecController implements Initializable {

  //  @FXML
   // private ListView<Reclamation> listRec;
    @FXML
    private TextField tf_recherche;
     @FXML
    private VBox vBoxMenu;

    private final Background focusBackground = new Background(new BackgroundFill(Color.web("#E4E4E4"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unfocusBackground = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    private final Border border = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));

    private HBox selectedMenuItem = null;
    private AnchorPane menuPane;
    @FXML
    private ImageView fpdf;
    @FXML
    private TableView<Reclamation> tabreclamation;
   
    @FXML
    private TableColumn<Reclamation, String> tfemail;
    @FXML
    private TableColumn<Reclamation, String> tfdate;
    @FXML
    private TableColumn<Reclamation, String> tftype;
    @FXML
    private TableColumn<Reclamation, String> tfmessage;
    @FXML
    private Button btnm;
    @FXML
    private TableColumn<Reclamation, String> tfaction;
    
    
    

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void selectMenueItem(MouseEvent event) {
        HBox hb = (HBox) event.getSource();

        if (!(hb).equals(selectedMenuItem)) {
            if (selectedMenuItem != null) {
                selectedMenuItem.setBackground(unfocusBackground);
            }

            selectedMenuItem = hb;
            selectedMenuItem.setBackground(focusBackground);
        }

        try {
            loadFxml("EventsView.fxml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFxml(String page) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource(page));
        menuPane.getChildren().add(newLoadedPane);
    }

    @FXML
    private void viewProfile(MouseEvent event) {
        try {
            loadFxml("profile.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> lr = sr.readReclamation();
        ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
        tabreclamation.setItems(data);
        this.loadReclamations();
                
    }    

    @FXML
    private void ajoutRec( MouseEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("reclamation.fxml"));
            Parent root=loader.load();
            ReclamationController aac=loader.getController();
            tabreclamation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListerecController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimerRec(ActionEvent event) {
       
        Reclamation R = new Reclamation();
        R = tabreclamation.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une reclamation à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer la reclamation?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ServiceReclamation SR= new ServiceReclamation();
                SR.deleteReclamation(R.getId());
                JOptionPane.showMessageDialog(null, "Reclamation supprimé");
                loadReclamations();
            }
    }
    }
    
    public void loadReclamations() {
      /*  ServiceReclamation SR = new ServiceReclamation();
        ArrayList<Reclamation> listeRec = (ArrayList<Reclamation>) SR.readReclamation();

        ObservableList observableList = FXCollections.observableArrayList(listeRec);
        tabreclamation.setItems(observableList);
        */
      tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
       tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
 tftype.setCellValueFactory(new PropertyValueFactory<>("type"));
 tfmessage.setCellValueFactory(new PropertyValueFactory<>("message"));
 // btnm.setCellValueFactory(new PropertyValueFactory<>("modifierRec"));


    }

    @FXML
    private void stats(MouseEvent event) throws ParseException {
        try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXBarChart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques reclamation");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
            gui.FXBarChartController controller = loader.getController();
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> lr = sr.readReclamation();
        ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
    
    tabreclamation.setItems(data);
        controller.setReclamationData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void modifierRec(ActionEvent event) {
       
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
            Parent root=loader.load();
            ModifierReclamationController aac=loader.getController();
            tabreclamation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListerecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void boutonpdf(MouseEvent event) throws ClassNotFoundException, SQLException, DocumentException {
       try {
       Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Calometre", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from Reclamation");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();
                       //OutputStream file = new FileOutputStream(new File("ProductReport.pdf"));
                    
                       my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString())); 
                       my_pdf_report.add(new Paragraph("calometre"));
                       my_pdf_report.add(new Paragraph("Listes des reclamation"));

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                       my_pdf_report.add(new Paragraph("Listes des reclamation"));
                             //Add Image
//		       Image image1 = Image.getInstance("filmouk.png");
//                       image1.scaleAbsolute(210, 210);
//                       my_pdf_report.add(image1);
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable((5));
                       my_report_table.setWidthPercentage(100); //Width 100%
			my_report_table.setSpacingBefore(10f); //Space before table
			my_report_table.setSpacingAfter(10f); //Space after table
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" Id"));
                                      table_cell.setBackgroundColor(BaseColor.ORANGE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Date"));
                                       table_cell.setBackgroundColor(BaseColor.ORANGE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Type"));
                                       table_cell.setBackgroundColor(BaseColor.ORANGE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Email"));
                                       table_cell.setBackgroundColor(BaseColor.ORANGE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("Message"));
                                       table_cell.setBackgroundColor(BaseColor.ORANGE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String id= rs.getString("id");
                                       table_cell=new PdfPCell(new Phrase(id));
                                       my_report_table.addCell(table_cell);
                                       
                                       String Date=rs.getString("Date");
                                       table_cell=new PdfPCell(new Phrase(Date));
                                       my_report_table.addCell(table_cell);
                                       
                                       String type=rs.getString("type");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       
                                       String email=rs.getString("email");
                                       table_cell=new PdfPCell(new Phrase(email));
                                       my_report_table.addCell(table_cell);
                                       
                                        String message = rs.getString("message");
                                       table_cell=new PdfPCell(new Phrase(message ));
                                       my_report_table.addCell(table_cell);
                                       
                                      
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "imprimer avec succes");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
    }


    }
    
    

