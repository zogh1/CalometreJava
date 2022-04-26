/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import service.cartService;
import service.categoryservice;
import service.productservice;

/**
 *
 * @author seifd
 */
public class FrontShopController implements Initializable {
    private static String Picture;
    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();
    
    cartService cart = new cartService();
    Cart cr = new Cart();
     @FXML
    private TextField qty;
     @FXML
    ImageView qrcode;
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
    
    public void setQrCode(){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = fn.getQrCodeInfo().get(0)+" is the most bought product ( "+fn.getQrCodeInfo().get(1)+" ) in your cart";
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
         prodImg.setStyle("-fx-alignment:center");
        prodName.setStyle("-fx-alignment:center");
        prodPrice.setStyle("-fx-alignment:center");
        prodDesc.setStyle("-fx-alignment:center");
        Category.setStyle("-fx-alignment:center");
        prodQty.setStyle("-fx-alignment:center");

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
        prodview.setItems(oblist);
}
    public void AddToChart() throws Exception{
         String quantity = qty.getText();
        p = prodview.getSelectionModel().getSelectedItem();
        p.setId(p.getId());
        int x =Integer.parseInt(quantity);
        cart.addProduct(p, 1, x);
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
        this.prodview();
        this.setQrCode();
    }
}