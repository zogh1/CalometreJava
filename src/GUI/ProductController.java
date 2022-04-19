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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class ProductController implements Initializable {

    private static String Picture;
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
    TableView<product> catview;
    @FXML
    private TableColumn<category, String> prodID;
    @FXML
    private TableColumn<category, String> prodName;
    @FXML
    private TableColumn<category, String> prodPrice;
    @FXML
    private TableColumn<category, String> prodDesc;
    @FXML
    private TableColumn<category, String> prodQty;
    @FXML
    private TableColumn<category, String> prodImg;
    ObservableList<product> oblist = FXCollections.observableArrayList();
    product p = null;

    public void editProd() throws java.io.IOException {
        String name = nameProd.getText();
        String price = PriceProd.getText();
        String desc = descProd.getText();
        String qty = qtyProd.getText();
        p = catview.getSelectionModel().getSelectedItem();
        p.setId(p.getId());
        p.setName(name);

        fn.updateproduct(p);
        Parent root = FXMLLoader.load(getClass().getResource("Product.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

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
                    ProductController.Picture = imageName + String.valueOf(imageName) + "." + extension;
                    Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        return ProductController.Picture;
    }

    public void addProduct() {
        String categorie = categ.getValue().toString();
        String name = nameProd.getText();
        String price = PriceProd.getText();
        String description = descProd.getText();
        String qty = qtyProd.getText();
        String selected = categ.getValue().toString();

        int id = Integer.parseInt(selected);

        if (name.isEmpty() ||price.isEmpty()|| description.isEmpty() || qty.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        } else {
            test.setName(name);
            test.setPrice(price);
            test.setDescription(description);

            test.setImage(Picture);
            cat.setId(id);
            fn.createproduct(cat, test);
        }


    }
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   List<category> listCateg = cat.readType();
        for (category list : listCateg) {
            int id = list.getId();
            categ.getItems().add(id);
        }
    }

}
