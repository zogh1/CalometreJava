package GUI;

import calometre.Calometre;
import entity.exercice;
import entity.product;
import entity.typeExercice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import main.MyListenerExercice;
import service.exerciceService;
import service.productservice;
import service.typeExerciceService;

public class ExerciceItemController {

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
    private void click(MouseEvent mouseEvent) {
        MyListenerExercice.onClickListener(exercice);
    }

    private exercice exercice;
    private MyListenerExercice MyListenerExercice;
    private MediaPlayer mediaPlayer;

    public void setData(exercice exercice, MyListenerExercice MyListenerExercice) {

        this.exercice = exercice;
        this.MyListenerExercice = MyListenerExercice;
        ExerciceName.setText(exercice.getNom());
        Type.setText(exercice.getNomtype_id().getNom());
        FileInputStream inputstream = null;

        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("videotest/" + exercice.getVideo()).toExternalForm()));
        mediaPlayer.setAutoPlay(false);
        ExVid.setMediaPlayer(mediaPlayer);
    }
}
