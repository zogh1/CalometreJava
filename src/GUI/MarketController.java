package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import calometre.Calometre;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entity.Cart;
import entity.category;
import entity.product;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import main.MyListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import service.cartService;
import service.categoryservice;
import service.productservice;

public class MarketController implements Initializable {

    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    cartService cart = new cartService();
    Cart cr = new Cart();
    ObservableList<product> oblist = FXCollections.observableArrayList();
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private TextField qty;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label prodDesc;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchprod;
    @FXML
    private GridPane grid;
    @FXML
    ImageView qrcode;
    product p = null;
    
                List<product> li = fn.getallproduct();
                
    private List<product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    String name;
    double price;
   private void RefreshPage() throws java.io.IOException{
    Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
}
 public void searchProduct() throws IOException{
     
     String searched = searchprod.getText();
   List<product> li = fn.searchProduct(searched);
        
   
         int column = 0;
        int row = 1;
        for (int i = 0; i < li.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(li.get(i), myListener);

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
            this.RefreshPage();
  
    }
 }
    
    private List<product> getData() {

        List<product> li = fn.getallproduct();
        List<product> products = new ArrayList<>();
        product product;
        for (int i = 0; i < li.size(); i++) {
            product = new product();
            product.setName(name);
            product.setPrice(price);

            product.setImage("C:\\Users\\seifd\\Documents\\images\\" + product.getImage());
            product.setColor("6A7324");
            products.add(product);

        }
        return products;
    }

    public void setQrCode() {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = fn.getQrCodeInfo().get(0) + " is the most bought product ( " + fn.getQrCodeInfo().get(1) + " ) in your cart";
        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = null;
        try {
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
        } catch (WriterException ex) {
            Logger.getLogger(Calometre.class.getName()).log(Level.SEVERE, null, ex);
        }

        qrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    private void setChosenProduct(product product) {
        fruitNameLable.setText(product.getName());
        fruitPriceLabel.setText(Calometre.CURRENCY + product.getPrice());
        prodDesc.setText(product.getDescription());
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\seifd\\Documents\\images\\" + product.getImage());
            image = new Image(inputstream, 100, 100, false, false);
            fruitImg.setImage(image);
            System.out.println(product.getCategory_id());
           

        } catch (FileNotFoundException ex) {
        }

        chosenFruitCard.setStyle("-fx-background-color: #" + product.getColor() + ";\n"
                + "    -fx-background-radius: 30;");

        int y = product.getId();
        System.out.println(y);

    }

    public void AddToChart() throws Exception {
        String quantity = qty.getText();

//      p.setId(p.getId());
        int x = Integer.parseInt(quantity);

        cart.addProduct(90, 1, x);
        this.RefreshPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setQrCode();
        products.addAll(this.getData());

        if (li.size() > 0) {
            setChosenProduct(li.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(product product) {
                    setChosenProduct(product);

                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < li.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(li.get(i), myListener);

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
