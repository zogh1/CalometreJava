/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Comment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.ServiceComment;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class CommentController implements Initializable {
    
    @FXML
    private ImageView image;
    @FXML
    private Label username;
    @FXML
    private Label commentcontent;
    @FXML
    private ImageView buton_jaime;
    @FXML
    private Label nbLike;
    
    private int idComment;
    
    private ServiceComment serviceComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceComment = new ServiceComment();
    }
    
    void setData(Comment comment) {
        idComment = comment.getId();
        username.setText("Zoghlami Wassim");
        commentcontent.setText(comment.getCommentcontent());
        nbLike.setText(comment.getLikecount() + "");
    }
    
    @FXML
    private void addLike(MouseEvent event) {
        serviceComment.addLike(idComment);
        
        int parsednbLike;
        try {
            parsednbLike = Integer.parseInt(nbLike.getText());
            nbLike.setText(parsednbLike + 1 + "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
