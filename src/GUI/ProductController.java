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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.stage.FileChooser;
import javafx.util.Callback;

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
    TableView<product> prodview;
    @FXML
    private TableColumn<product, String> prodID;
    @FXML
    private TableColumn<product, String> prodName;
    @FXML
    private TableColumn<product, String> prodPrice;
    @FXML
    private TableColumn<product, String> prodDesc;
    @FXML
    private TableColumn<product, String> prodQty;
    @FXML
    private TableColumn<product, Image> prodImg;
    @FXML
    private TableColumn<category, Integer> Category;
    ObservableList<product> oblist = FXCollections.observableArrayList();
    product p = null;
    
    private void prodview() {

        // TODO
        List<product> li = fn.getallproduct();
        li.forEach(e -> {
            oblist.add(e);
            prodID.setCellValueFactory(new PropertyValueFactory<>("id"));
            prodName.setCellValueFactory(new PropertyValueFactory<>("name"));
            prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            prodDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            prodQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            Category.setCellValueFactory(new PropertyValueFactory<>("category_id"));
          
            });
       
            //prodImg.setCellValueFactory(new PropertyValueFactory<>("image"));
            
    
        prodImg.setCellValueFactory((CellDataFeatures<product, Image> b) -> {
            String productImg = b.getValue().getImage();
            FileInputStream inputstream = null;
            try {
                inputstream = new FileInputStream("C:\\Users\\seifd\\Documents\\images\\" + productImg);
            } catch (FileNotFoundException ex) {
            }

            return new SimpleObjectProperty<>(new Image(inputstream, 100, 100, false, false));
        });
        prodImg.setCellFactory((TableColumn<product, Image> b) -> new TableCell<product, Image>() {
            @Override
            protected void updateItem(Image i, boolean empty) {
                super.updateItem(i, empty);
                setText(null);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                double width = 60;
                double height = 60;
                ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                setGraphic(imageView);
            }
        });
 /*Category.setCellValueFactory(new PropertyValueFactory<>("category_id"),ObservableValue<String>(){

                @Override
                public ObservableValue<String> call(CellDataFeatures<Enseignant, String> param) {
                    return new SimpleStringProperty(param.getValue().getMatiere().getIntitule());
                }
            });*/
        prodview.setItems(oblist);

    }
    
    private void refreshtables() throws java.io.IOException{
    Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
}
    
            public void GoToMarket() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    public void GoToCat() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    public void GoToStats() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("statProduits.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }
    public void editProd() throws java.io.IOException {
        String selected = categ.getValue().toString();
        category catname = fn1.findByName(selected);
        String name = nameProd.getText();
        String price = PriceProd.getText();
        String description = descProd.getText();
        String qty = qtyProd.getText();
        int x = Integer.parseInt(catname.getName());
        int prix= Integer.parseInt(price);
        int quantity= Integer.parseInt(qty);
        p = prodview.getSelectionModel().getSelectedItem();
        p.setId(p.getId());
        p.setName(name);
        p.setPrice(prix);
        p.setDescription(description);
        p.setQuantity(quantity);
        cat.setId(x);
        fn.updateproduct(p, cat);
        this.refreshtables();
    }
public void deleteprod() throws java.io.IOException {
        
        p = prodview.getSelectionModel().getSelectedItem();
        p.setId(p.getId());
        

        fn.deleteproduct(p.getId());
        this.refreshtables();
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

    public void addProduct() throws IOException {
       
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
        int prix= Integer.parseInt(price);
        int quantity= Integer.parseInt(qty);
        
       

        if ( name.isEmpty()) {
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
            fn.createproduct(test,cat );
           
        }
        this.refreshtables();


    }
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   List<category> listCateg = fn1.getallcategory();
        for (category list : listCateg) {
            String id = list.getName();
            categ.getItems().add(id);
        }
        this.prodview();
    }

}
