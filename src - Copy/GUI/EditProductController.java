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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class EditProductController implements Initializable {

    static String Picture;
    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    @FXML
    private ComboBox categ;
    @FXML
    private TextField nameProd = new TextField();
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
                if (!(extension.equals("png") || !extension.equals("jpg") || !extension.equals("jpeg"))) {
                    System.err.println("wrong format");
                } else {
                    Random random = new Random();
                    int imageName = random.nextInt(30000000);
                    String pathname = "C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\products\\" + imageName + String.valueOf(imageName) + "." + extension;
                    File file1 = new File(pathname);
                    EditProductController.Picture = imageName + String.valueOf(imageName) + "." + extension;
                    Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        return EditProductController.Picture;
    }

    private void refreshtables() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public product itemSelected(product y) {

        test.setId(y.getId());
        test.setName(y.getName());
        test.setPrice((int) y.getPrice());
        test.setDescription(y.getDescription());
        test.setQuantity(y.getQuantity());
        test.setCategory_id(y.getCategory_id());
        test.setImage(y.getImage());
        nameProd.setText(test.getName());
        PriceProd.setText(String.valueOf(test.getPrice()));
        descProd.setText(test.getDescription());
        qtyProd.setText(String.valueOf(test.getQuantity()));
        categ.setValue(test.getCategory_id().getName());
        Picture = test.getImage();

        return test;
    }

    public void editProd() throws java.io.IOException {

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
            System.out.println("1 : " + nameProd.getText());
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

        if (error == 0) {
            System.out.println("2 : " + nameProd.getText());
            String selected = categ.getValue().toString();
            category catname = fn1.findByName(selected);
            int x = Integer.parseInt(catname.getName());
            int quantity = Integer.parseInt(qtyProd.getText());
            test.getId();
            test.setName(nameProd.getText());
            test.setPrice(Double.parseDouble(PriceProd.getText()));
            test.setDescription(descProd.getText());
            test.setQuantity(quantity);
            test.setImage(Picture);
            cat.setId(x);
            if (fn.updateproduct(test, cat)) {
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
