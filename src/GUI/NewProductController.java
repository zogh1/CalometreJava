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
import javafx.scene.control.TextField;
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

        String selected = categ.getValue().toString();
        category catname = fn1.findByName(selected);
        String name = nameProd.getText();
        String price = PriceProd.getText();
        String description = descProd.getText();
        String qty = qtyProd.getText();

        int x = Integer.parseInt(catname.getName());
        System.out.println(catname);
        // int x = catname;
        //System.out.println(x);
        int prix = Integer.parseInt(price);
        int quantity = Integer.parseInt(qty);

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {

            test.setName(name);
            test.setPrice(prix);
            test.setDescription(description);
            test.setQuantity(quantity);
            test.setImage(Picture);
            cat.setId(x);
            fn.createproduct(test, cat);

        }
        Stage stage = (Stage) closewindow.getScene().getWindow();
        stage.close();
        this.refreshtables();

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
