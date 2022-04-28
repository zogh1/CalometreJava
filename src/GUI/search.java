/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Cart;
import entity.category;
import entity.product;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import main.MyListener;
import service.cartService;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class search {

    private MyListener myListener;
    @FXML
    private GridPane grid;
    @FXML
    private TextField searchprod;
    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    cartService cart = new cartService();
    Cart cr = new Cart();

   
}
