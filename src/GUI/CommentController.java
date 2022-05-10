/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Comment;
import entity.user;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import service.ServiceComment;
import service.userservice;
import util.session;

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
    private userservice userservice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceComment = new ServiceComment();
        userservice = new userservice();
    }
    
    void setData(Comment comment) throws FileNotFoundException {
        idComment = comment.getId();
        user user = userservice.findById(comment.getUser_id());
        username.setText(user.getFirstname()+" " + user.getLastname());
        commentcontent.setText(comment.getCommentcontent());
        if(user.getId() == session.getUser().getId())
            username.setTextFill(Color.RED);
        else
            username.setTextFill(Color.BLUE);
        nbLike.setText(comment.getLikecount() + "");
        Image i = new Image(new FileInputStream("C:\\Users\\wassim\\Desktop\\Pidev3A\\Calometre\\public\\uploads\\profilePicture\\"+ user.getProfile_picture()));
        
        
        image.setImage(i);
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
