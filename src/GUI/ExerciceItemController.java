package GUI;

import calometre.Calometre;
import entity.exercice;
import entity.typeExercice;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import main.MyListenerExercice;
import org.controlsfx.control.Rating;
import service.exerciceService;
import service.typeExerciceService;

public class ExerciceItemController implements Initializable {

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

    static int myRating;

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

    public void rateSmth() throws IOException {

        double value = rate.getRating();
        double totalrating = value + exercice.getTotal_rating();
        System.out.println("value : " + value);
        int nbCounts = exercice.getCount();
        System.out.println("nbcount : " + nbCounts);
        double rating = exercice.getRating();
        System.out.println("rating : " + rating);
        double newValue = totalrating / nbCounts;
        System.out.println(value + rating);
        System.out.println("newvalue : " + newValue);
        fn.addRating(newValue, exercice.getId(), nbCounts, totalrating);
        Parent root = FXMLLoader.load(getClass().getResource("BackExerciceView.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* rate.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newValue) {
                int value = (int) rate.getRating();
                int count = exercice.getCount() + 1;
                System.out.println(count);
                System.out.println(exercice.getCount());
                int newRating = value / count;
                System.out.println(newRating);
                fn.addRating(newRating, exercice.getId(), count);
            }
        });*/
    }
}
