/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.RecRep;
import entity.Reclamation;
import entity.Reponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.ServiceReclamation;
import service.ServiceReponse;
import util.session;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ReponseController implements Initializable {

    @FXML
    private ImageView addreponse;
    // @FXML
    // private ListView<Reponse> listerep;
    @FXML
    private TableView<RecRep> tabrep;
    @FXML
    private TableColumn<RecRep, String> tfmail;
    @FXML
    private TableColumn<RecRep, String> tfdate;
    @FXML
    private TableColumn<RecRep, String> tftype;
    @FXML
    private TableColumn<RecRep, String> tfmessage;
    private TableColumn<RecRep, String> tfreponse;
    @FXML
    private ImageView fpdf;
    @FXML
    private TableColumn<Reponse, String> tfidrec;
    @FXML
    private TableColumn<Reponse, String> tf_reponse;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void ajouterReponse(MouseEvent event) throws IOException {

         Parent root = FXMLLoader.load(getClass().getResource("AjoutReponse.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
        }
    
 public void GoToProd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToUserListAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    
            public void GoToResp() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
     public void GoToCat() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    public void GoToStatAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userstats.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    @FXML
    private void modifierReponse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReponse.fxml"));
            Parent root = loader.load();
            ModifierReponseController aac = loader.getController();
            tabrep.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadReponses() {
        /* ServiceReponse sr = new ServiceReponse();
        ArrayList<Reponse> listeReponse = (ArrayList<Reponse>) sr.readReponse();

        ObservableList observableList = FXCollections.observableArrayList(listeReponse);
        tabrep.setItems(observableList);*/
        ServiceReponse st = new ServiceReponse();
        List<RecRep> lrep = st.getAll();
        System.out.println(lrep);

        ObservableList<RecRep> data = FXCollections.observableArrayList(lrep);

        tfmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tftype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tfmessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        tfidrec.setCellValueFactory(new PropertyValueFactory<>("id"));
        tf_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));

        tabrep.setItems(data);

    }

    @FXML
    private void supprimerReponse(MouseEvent event) throws IOException {
        
        RecRep R =tabrep.getSelectionModel().getSelectedItem();
        if (R == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une reponse à supprimer");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer la reponse?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                ServiceReponse SR = new ServiceReponse();
                System.out.println(R.getIdRep());
                SR.deleteReponse(R.getIdRep());
                JOptionPane.showMessageDialog(null, "Reponse supprimé");
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("reponse.fxml"));
                Parent root = loader.load();
                tabrep.getScene().setRoot(root);
            }
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

            table_cell = new PdfPCell(new Phrase(" Id"));
            table_cell.setBackgroundColor(BaseColor.ORANGE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Date"));
            table_cell.setBackgroundColor(BaseColor.ORANGE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Type"));
            table_cell.setBackgroundColor(BaseColor.ORANGE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Email"));
            table_cell.setBackgroundColor(BaseColor.ORANGE);
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Message"));
            table_cell.setBackgroundColor(BaseColor.ORANGE);
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String id = rs.getString("id");
                table_cell = new PdfPCell(new Phrase(id));
                my_report_table.addCell(table_cell);

                String Date = rs.getString("Date");
                table_cell = new PdfPCell(new Phrase(Date));
                my_report_table.addCell(table_cell);

                String type = rs.getString("type");
                table_cell = new PdfPCell(new Phrase(type));
                my_report_table.addCell(table_cell);

                String email = rs.getString("email");
                table_cell = new PdfPCell(new Phrase(email));
                my_report_table.addCell(table_cell);

                String message = rs.getString("message");
                table_cell = new PdfPCell(new Phrase(message));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loadReponses();

    }
    //manbadelsh hedhi b reponse !! lhtha
}
