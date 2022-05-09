/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author seifd
 */
public class mediplayertest implements Initializable {
    @FXML
    private MediaView ExVid;
     private static final String MEDIA_URL = "videotest/Dubstep Bird (Original 5 Sec Video).mp4";
  
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(location.toString());
        System.out.println(this.getClass().getResource(MEDIA_URL).toExternalForm());
    
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        ExVid.setMediaPlayer(mediaPlayer);
}
}
