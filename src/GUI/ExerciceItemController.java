package GUI;

import calometre.Calometre;
import entity.exercice;
import entity.product;
import entity.typeExercice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import main.MyListenerExercice;
import org.controlsfx.control.Rating;
import service.exerciceService;
import service.productservice;
import service.typeExerciceService;

public class ExerciceItemController implements Initializable{

    exerciceService fn = new exerciceService();
    exercice ex = new exercice();
    typeExerciceService fn1 = new typeExerciceService();
    typeExercice tex = new typeExercice();
    @FXML
    private Label ExerciceName;
    @FXML
    private Label Type;
    @FXML
    private MediaView ExVid;
    @FXML
    private Rating rate;

    @FXML
    private void click(MouseEvent mouseEvent) {
        MyListenerExercice.onClickListener(exercice);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        int i = (int) rate.getRating();
    }

    private exercice exercice;
    private MyListenerExercice MyListenerExercice;
    private MediaPlayer mediaPlayer;

    public void setData(exercice exercice, MyListenerExercice MyListenerExercice) {

        this.exercice = exercice;
        System.out.println(exercice.getRating());
        if (exercice.getRating() != 0) {
            rate.setRating((double) exercice.getRating());
        }
        this.MyListenerExercice = MyListenerExercice;
        ExerciceName.setText(exercice.getNom());
        Type.setText(exercice.getNomtype_id().getNom());
        FileInputStream inputstream = null;

        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("videotest/" + exercice.getVideo()).toExternalForm()));
        mediaPlayer.setAutoPlay(false);
        ExVid.setMediaPlayer(mediaPlayer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
rate.ratingProperty().addListener(new ChangeListener<Number> (){
    @Override
    public void changed(ObservableValue<? extends Number> ov, Number old, Number newValue) {
        int value = (int) rate.getRating();
        fn.addRating(value, exercice.getId());
    } 
});
    }
}
