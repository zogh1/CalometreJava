/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;

import entity.category;
import entity.product;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class NewProductController implements Initializable {

    static String Picture;
    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    @FXML
    private ComboBox categ;
    @FXML
    private TextField nameProd;
    @FXML
    private TextField PriceProd;
    @FXML
    private TextField descProd;
    @FXML
    private TextField qtyProd;
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
                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))) {
                    System.err.println("wrong format");
                } else {
                    Random random = new Random();
                    int imageName = random.nextInt(30000000);
                    String pathname = "C:\\Users\\Seifd\\Documents\\images\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    NewProductController.Picture = imageName + String.valueOf(imageName) + "." + extension;
                    Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        return NewProductController.Picture;
    }

    private void refreshtables() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void addProduct() throws java.io.IOException {

        int error = 0;

        if (categ.getValue() == null) {

            categ.setStyle("-fx-prompt-text-fill: red;");
            categ.setPromptText("Chose something!");
            new animatefx.animation.Shake(categ).play();
            error++;

        } else {
            categ.setStyle(null);

        }

        if (nameProd.getText().isEmpty()) {

            nameProd.setStyle("-fx-prompt-text-fill: red; -fx-font-size: 10pt;");
            nameProd.setPromptText("Please verify the product name!");
            new animatefx.animation.Shake(nameProd).play();
            error++;

        } else {
            nameProd.setStyle(null);

        }
        if (PriceProd.getText().isEmpty() || (!PriceProd.getText().matches("([0-9]*)\\.([0-9]*)") && !PriceProd.getText().matches("[0-9]+"))) {

            PriceProd.setStyle("-fx-prompt-text-fill: red; -fx-font-size: 10pt;");
            PriceProd.setText("");
            PriceProd.setPromptText("Please verify the product price!");
            new animatefx.animation.Shake(PriceProd).play();
            error++;
        } else {
            PriceProd.setStyle(null);

        }
        if (descProd.getText().isEmpty()) {

            descProd.setStyle("-fx-prompt-text-fill: red; -fx-font-size: 10pt;");
            descProd.setPromptText("Please verify the product description!");
            new animatefx.animation.Shake(descProd).play();
            error++;
        } else {
            descProd.setStyle(null);

        }
        if (qtyProd.getText().isEmpty() || !qtyProd.getText().matches("[0-9]+")) {

            qtyProd.setStyle("-fx-prompt-text-fill: red; -fx-font-size: 10pt;");
            qtyProd.setText("");
            qtyProd.setPromptText("Please verify the product quantity!");
            new animatefx.animation.Shake(qtyProd).play();
            error++;
        } else {
            qtyProd.setStyle(null);

        }

        // int x = catname;
        //System.out.println(x);
        if (error == 0) {
            String selected = categ.getValue().toString();
            category catname = fn1.findByName(selected);
            int x = Integer.parseInt(catname.getName());
            int quantity = Integer.parseInt(qtyProd.getText());
            test.setName(nameProd.getText());
            test.setPrice(Double.parseDouble(PriceProd.getText()));
            test.setDescription(descProd.getText());
            test.setQuantity(quantity);
            test.setImage(Picture);
            cat.setId(x);
            if (fn.createproduct(test, cat)) {
                this.refreshtables();
                Stage stage = (Stage) closewindow.getScene().getWindow();
                stage.close();
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<category> listCateg = fn1.getallcategory();
        for (category list : listCateg) {
            String id = list.getName();
            categ.getItems().add(id);
        }

    }

}
//private void onValiderClicked(MouseEvent event) throws Exception {
//        
//        Restaurant r = new Restaurant();
//        r.setNom(input_nom.getText());
//        r.setImage(imagelink);
//        r.setNum(Integer.parseInt(num.getText()));
//        r.setHorraire_ouverture(Date.valueOf(dateopen.getValue()));
//        r.setHorraire_fermeture(Date.valueOf(dateclose.getValue()));
//        r.setNom_reg(region.getValue());
//                
//if((Integer.toString(r.getNum()).length()!=8)||(r.getNom().length()==0)||(r.getHorraire_fermeture().toString().length()==0)||(r.getHorraire_ouverture().compareTo(Date.valueOf(LocalDate.now()))<0)||(r.getHorraire_ouverture().compareTo(r.getHorraire_fermeture())>0)){
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information");
//        alert.setHeaderText(null);
//        alert.setContentText("Verifiez vos données");
//
//        alert.show();
//        }else{ 
//        RestaurantService irestau = new RestaurantService();
//        if (restaurant == null) {
//            irestau.ajouterOrganisateur(r);
//        } else {
//            
//            r.setId(restaurant.getId());
//            irestau.ModifierRestaurant(r);
//            region = null;
//        }
//
//        input_nom.clear();
//        num.clear();}
//
//
//    }*