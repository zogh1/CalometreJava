package GUI;

import calometre.Calometre;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entity.Cart;
import entity.CartItem;
import entity.category;
import entity.product;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MyListener;
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
    private ComboBox catBox;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private TextField qty;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label prodDesc;
    @FXML
    private Label ItemsCount;

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

    private List<product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    String name;
    double price;

    private void RefreshPage() throws java.io.IOException {

        Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToCart() throws java.io.IOException {
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

    public void searchProduct() throws java.io.IOException {

        String searched = searchprod.getText();

        if (searched != null) {
            grid.getChildren().clear();
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

            }
        } else {
            this.RefreshPage();
        }
    }

    private void setChosenProduct(product product) {

        fruitNameLable.setText(product.getName());
        fruitPriceLabel.setText(Calometre.CURRENCY + product.getPrice());
        prodDesc.setText(product.getDescription());
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\Souhail\\Documents\\images\\" + product.getImage());
            image = new Image(inputstream, 100, 100, false, false);
            fruitImg.setImage(image);

        } catch (FileNotFoundException ex) {
        }
        if ("Food".equals(product.getCategory_id().getName())) {
            product.setColor("3CB371");
        }
        if ("Brands".equals(product.getCategory_id().getName())) {
            product.setColor("9370DB");
        }
        if ("Sport".equals(product.getCategory_id().getName())) {
            product.setColor("4169E1");
        }
        chosenFruitCard.setStyle("-fx-background-color: #" + product.getColor() + ";\n"
                + "    -fx-background-radius: 30;");

        int y = product.getId();

    }

    public void TotalText() {

        List<CartItem> crtitem = cart.loadProductsFromCart(1);
        ItemsCount.setText("ItemsCount:" + crtitem.size());
    }

    public product itemid(product product) {

        int y = product.getId();
        test.setId(y);
        return test;
    }

    public void AddToChart() throws Exception {
        cart.createCart(1, 0, 1);
        String quantity = qty.getText();
        int error = 0;
        if (!quantity.matches("[1-9]+")) {

            qty.setStyle("-fx-prompt-text-fill: red; -fx-font-size: 10pt;");
            qty.setText("");
            qty.setPromptText("Please verify Quantity!");
            new animatefx.animation.Shake(qty).play();
            error++;
        } else {
            qty.setStyle(null);
        }
        if (error == 0) {
            int x = Integer.parseInt(quantity);
            int y = test.getId();
            cart.addProduct(y, 1, x);
            this.RefreshPage();
        }
    }

    public void ShowByCategory() throws IOException {
        String catego = (String) catBox.getValue();
        System.out.println(catego);
        List<product> li;

        grid.getChildren().clear();
        if ("All".equals(catego)) {
            li = fn.getallproduct();
        } else {
            li = fn.searchByCategory(catego);
        }

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

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setQrCode();

        List<product> li = fn.getallproduct();
        if (li.size() > 0) {
            setChosenProduct(li.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(product product) {
                    setChosenProduct(product);
                    itemid(product);

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
        this.TotalText();
        List<category> listCateg = fn1.getallcategory();
        for (category list : listCateg) {
            String id = list.getName();
            catBox.getItems().add(id);
        }
        catBox.getItems().add("All");
    }

}
