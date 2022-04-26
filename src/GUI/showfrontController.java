/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.category;
import entity.product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class showfrontController {
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
    private TableColumn<category, String> Category;
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
          
       
            //prodImg.setCellValueFactory(new PropertyValueFactory<>("image"));
            
        });
        prodImg.setCellValueFactory((TableColumn.CellDataFeatures<product, Image> b) -> {
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
}
