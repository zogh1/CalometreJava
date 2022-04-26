/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Comment;
import entity.Event;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.ServiceComment;
import service.ServiceEvent;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class DetailsController implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label date_fin;
    @FXML
    private Label date_debut;
    @FXML
    private Label nb_applyed;
    @FXML
    private Label nombre_participants;
    @FXML
    private Label id;
    @FXML
    private TextArea comment_area;
    @FXML
    private Button bouton_ajouter;
    @FXML
    private Label nb_commentaires;
    @FXML
    private ScrollPane comment_list;

    private ServiceEvent serviceEvent;
    private ServiceComment serviceComment;
    @FXML
    private VBox commentsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceEvent = new ServiceEvent();
        serviceComment = new ServiceComment();

        comment_area.textProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals("")) {
                bouton_ajouter.setDisable(false);
            } else {
                bouton_ajouter.setDisable(true);
            }
        });
    }

    public void setData(int idEvent) throws FileNotFoundException {
        Event event = serviceEvent.getEventById(idEvent);
        System.out.println(event + "+++++++");
        this.id.setText("" + event.getId());

        this.nom.setText(event.getNom());

        this.description.setText(event.getNom());

        this.date_fin.setText(event.getDate_debut());

        this.date_debut.setText(event.getDate_fin());

        String nbApplyed = "" + serviceEvent.getNombre_participants_byevent(event.getId());
        this.nb_applyed.setText(nbApplyed);

        this.nombre_participants.setText("" + event.getNombre_participants());

        Image i = new Image(new FileInputStream("C:\\Users\\wassim\\Desktop\\Pidev3A\\Calometre\\public\\uploads\\Event_images\\"+ event.getImage()));
        
        
        image.setImage(i);
        setComments();
    }

    @FXML
    private void addComment(MouseEvent event) {
        int parsedId;
        try {
            Comment comment = new Comment();

            parsedId = Integer.parseInt(id.getText());
            comment.setEvent_id(parsedId);

            comment.setCommentcontent(comment_area.getText());

            Date now = new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String mysqlDateString = formatter.format(now);
            comment.setCommentdate(mysqlDateString);

            comment.setUser_id(1);

            comment.setLikecount(0);

            serviceComment.addComment(comment);

            commentsList.getChildren().clear();
            setComments();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setComments() {
        int parsedId;
        try {
            parsedId = Integer.parseInt(id.getText());

            List<Comment> comments = serviceComment.getCommentsByEvent(parsedId);

            nb_commentaires.setText(comments.size()+"");
            System.out.println(comments.size());
            try {
                for (int i = 0; i < comments.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("comment.fxml"));

                    VBox commentBox = loader.load();

                    CommentController commentController = loader.getController();
                    commentController.setData(comments.get(i));

                    commentsList.getChildren().add(commentBox);
                }
            } catch (IOException ioe) {
                System.out.println("Error While Adding comments : " + ioe.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
