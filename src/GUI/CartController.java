package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import calometre.Calometre;

import entity.Cart;
import entity.CartItem;
import entity.category;
import entity.product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import java.util.List;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import service.cartService;
import service.categoryservice;
import service.productservice;

public class CartController implements Initializable {

    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    cartService cart = new cartService();
    Cart cr = new Cart();
    CartItem crtProd = new CartItem();
    ObservableList<CartItem> oblist = FXCollections.observableArrayList();
    @FXML
    private ScrollPane scroll;
 
    @FXML
    private GridPane grid;

    private List<CartItem> CartItem = new ArrayList<>();
    private Image image;
   
    String name;
    double price;

  

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<CartItem> li = cart.loadProductsFromCart(1);

     
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < li.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item-Shop.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemCartController ItemCartController = fxmlLoader.getController();
                ItemCartController.setData(li.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }

}
