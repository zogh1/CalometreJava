package GUI;

import GUI.ItemController;
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

import entity.exercice;

import entity.typeExercice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import main.MyListenerExercice;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import service.cartService;
import service.categoryservice;
import service.exerciceService;
import service.productservice;
import service.typeExerciceService;

public class frontExerciceViewController implements Initializable {

    exerciceService fn = new exerciceService();
    exercice ex = new exercice();

    typeExerciceService fn1 = new typeExerciceService();
    typeExercice tex = new typeExercice();

    ObservableList<exercice> oblist = FXCollections.observableArrayList();
    @FXML
    private VBox ChosenProd;

    @FXML
    private Label ExerciceName;
    @FXML
    private Label ExDesc;
    @FXML
    private Label ExObj;
    @FXML
    private Label Type;
    @FXML
    private MediaView ExVid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchprod;
    @FXML
    private GridPane grid;

    exercice p = null;

    private List<exercice> exercices = new ArrayList<>();
    private Image image;
    private MyListenerExercice MyListenerExercice;
    private static final String MEDIA_URL = "videotest/";
    private MediaPlayer mediaPlayer;
    String name;
    @FXML
    private HBox boxvolume;
    @FXML
    private Rating rate;

    public void GoToFront() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("frontexercice.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    private void RefreshPage() throws java.io.IOException {

        Parent root = FXMLLoader.load(getClass().getResource("BackExerciceView.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void AddMoreExercice() throws java.io.IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddExercice.fxml"));
            /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 800, 550);
            Stage stage = new Stage();
            stage.setTitle("New Exercice");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void searchExercice() throws java.io.IOException {

        String searched = searchprod.getText();

        if (searched != null) {
            grid.getChildren().clear();
            List<exercice> li = fn.searchExercice(searched);

            int column = 0;
            int row = 1;
            for (int i = 0; i < li.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ExerciceItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExerciceItemController ExerciceItemController = fxmlLoader.getController();
                ExerciceItemController.setData(li.get(i), MyListenerExercice);

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

    private void setChosenExercice(exercice exercice) {

        ExerciceName.setText(exercice.getNom());
        Type.setText(exercice.getNomtype_id().getNom());
        ExDesc.setText(exercice.getDescription());
        ExObj.setText(exercice.getObjectif());
        ExDesc.setMaxWidth(Region.USE_COMPUTED_SIZE);
        ExDesc.setWrapText(true);
        System.out.println(exercice.getRating());
        if (exercice.getRating() != 0) {
            rate.setRating(exercice.getRating());
        }

        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL + exercice.getVideo()).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        ExVid.setMediaPlayer(mediaPlayer);

        if ("Food".equals(exercice.getNomtype_id().getNom())) {
            exercice.setColor("3CB371");
        }
        if ("Brands".equals(exercice.getNomtype_id().getNom())) {
            exercice.setColor("9370DB");
        }
        if ("Sport".equals(exercice.getNomtype_id().getNom())) {
            exercice.setColor("4169E1");
        }
        ChosenProd.setStyle("-fx-background-color: #" + exercice.getColor() + ";\n"
                + "    -fx-background-radius: 30;");

        int y = exercice.getId();

    }

    public exercice itemid(exercice exercice) {

        ex.setId(exercice.getId());
        ex.setNom(exercice.getNom());
        ex.setObjectif(exercice.getObjectif());
        ex.setDescription(exercice.getDescription());
        ex.setNomtype_id(exercice.getNomtype_id());
        ex.setVideo(exercice.getVideo());

        return ex;
    }

    public void deleteExercice() throws java.io.IOException {
        int y = ex.getId();

        fn.deleteExercice(y);
        this.RefreshPage();
    }

    public void GoToEditExercice() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditExercice.fxml"));
            Parent root = (Parent) loader.load();
            EditExerciceController secController = loader.getController();
            secController.itemSelected(ex);
            Stage stage = new Stage();
            stage.setTitle("Edit Exercice");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<exercice> li = fn.readExercice();
        if (li.size() > 0) {
            setChosenExercice(li.get(0));
            MyListenerExercice = new MyListenerExercice() {
                @Override
                public void onClickListener(exercice exercice) {
                    setChosenExercice(exercice);
                    itemid(exercice);

                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < li.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ExerciceItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExerciceItemController ExerciceItemController = fxmlLoader.getController();
                ExerciceItemController.setData(li.get(i), MyListenerExercice);

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
