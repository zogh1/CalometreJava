package GUI;

import calometre.Calometre;
import entity.Cart;
import entity.category;
import entity.product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class BackProductsController implements Initializable {

    productservice fn = new productservice();
    product test = new product();

    categoryservice fn1 = new categoryservice();
    category cat = new category();

    cartService cart = new cartService();
    Cart cr = new Cart();

    @FXML
    private VBox ChosenProd;

    @FXML
    private Label ProdName;
    @FXML
    private Label prodDesc;
    @FXML
    private Label ProdPrice;
    @FXML
    private ImageView ProdImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchprod;
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox catBox;
    private Image image;
    private MyListener myListener;
    String name;
    double price;

    public void GoToMarket() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToUserListAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToStatAdmin() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userstats.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToCat() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    private void RefreshPage() throws java.io.IOException {

        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void AddMoreProduct() throws java.io.IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddProduct.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
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

    public void searchProduct() throws IOException {
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

        ProdName.setText(product.getName());
        ProdPrice.setText(Calometre.CURRENCY + product.getPrice());
        prodDesc.setText(product.getDescription());

        prodDesc.setMaxWidth(Region.USE_COMPUTED_SIZE);
        prodDesc.setWrapText(true);

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("C:\\Users\\Souhail\\Documents\\images\\" + product.getImage());
            image = new Image(inputstream, 100, 100, false, false);
            ProdImg.setImage(image);

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
        ChosenProd.setStyle("-fx-background-color: #" + product.getColor() + ";\n"
                + "    -fx-background-radius: 30;");

        int y = product.getId();

    }

    public product itemid(product product) {

        test.setId(product.getId());
        test.setName(product.getName());
        test.setPrice(product.getPrice());
        test.setDescription(product.getDescription());
        test.setQuantity(product.getQuantity());
        test.setCategory_id(product.getCategory_id());
        test.setImage(product.getImage());

        return test;
    }

    public void deleteprod() throws IOException {
        int y = test.getId();
        System.out.println(y);
        fn.deleteproduct(y);
        this.RefreshPage();

    }

    public void GoToEditProduct() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
            Parent root = (Parent) loader.load();
            EditProductController secController = loader.getController();
            secController.itemSelected(test);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void GoToEditProduct() throws IOException {
//      try {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("EditProduct.fxml"));
//        /*
//         * if "fx:controller" is not set in fxml
//         * fxmlLoader.setController(NewWindowController);
//         */
//
//        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
//        Stage stage = new Stage();
//        stage.setTitle("Edit Product");
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException e) {
//        Logger logger = Logger.getLogger(getClass().getName());
//        logger.log(Level.SEVERE, "Failed to create new Window.", e);
//    }
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        List<category> listCateg = fn1.getallcategory();
        for (category list : listCateg) {
            String id = list.getName();
            catBox.getItems().add(id);
        }
        catBox.getItems().add("All");
    }

}
