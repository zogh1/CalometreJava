/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Souhail
 */
public class ProfileController implements Initializable {

    public void LinkToChagePassword() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("changepassword.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
