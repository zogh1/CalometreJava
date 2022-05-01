package GUI;

import calometre.Calometre;
import entity.Cart;
import entity.CartItem;
import entity.product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import service.cartService;
import service.productservice;

public class ItemCartController {

    productservice fn = new productservice();
    product test = new product();
    CartItem crtProd = new CartItem();
    cartService cart = new cartService();
    Cart cr = new Cart();
    @FXML
    private Label nameLabel;
    @FXML
    private TextField qtyCart;
    @FXML
    private Button Del;

    @FXML
    private Label TotalItem;
    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    private CartItem CartItem;

    private void RefreshPage() throws java.io.IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Cart.fxml"));
            /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 800, 550);
            Stage stage = new Stage();
            stage.setTitle("Cart Items");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void setData(CartItem CartItem) {

        this.CartItem = CartItem;

        nameLabel.setText(CartItem.getProduct().getName());
        priceLable.setText(Calometre.CURRENCY + CartItem.getProduct().getPrice());
        TotalItem.setText(Calometre.CURRENCY + CartItem.getProduct().getPrice() * CartItem.getQuantity());
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\seifd\\Documents\\images\\" + CartItem.getProduct().getImage());
            Image image = new Image(inputstream, 100, 100, false, false);
            img.setImage(image);
        } catch (FileNotFoundException ex) {
        }
    }

    public void DelQtyCart() throws IOException {

        this.CartItem = CartItem;
        int y = CartItem.getProduct().getId();
        cart.removeProduct(y, 1);
        Stage stage = (Stage) Del.getScene().getWindow();
        stage.close();
        this.RefreshPage();
    }

    public void AddQtyCart() throws Exception {
        String quantity = qtyCart.getText();
        int x = Integer.parseInt(quantity);
        this.CartItem = CartItem;
        int y = CartItem.getProduct().getId();
        System.out.println(y);
        cart.addProduct(y, 1, x);
        Stage stage = (Stage) Del.getScene().getWindow();
        stage.close();
        this.RefreshPage();
    }
}
