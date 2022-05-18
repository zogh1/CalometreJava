package GUI;

import calometre.Calometre;
import calometre.MyListener;
import entity.product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.productservice;

public class ItemController {

    productservice fn = new productservice();
    product test = new product();
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    private product product;
    private MyListener myListener;

    public void setData(product product, MyListener myListener) {

        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText(Calometre.CURRENCY + product.getPrice());
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("C:\\xampp\\htdocs\\Calometre-main\\public\\uploads\\products\\" + product.getImage());
            Image image = new Image(inputstream, 100, 100, false, false);
            img.setImage(image);
        } catch (FileNotFoundException ex) {
        }
    }
}
