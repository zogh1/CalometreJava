/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.user;
import interfacee.userInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class UsersListController implements Initializable {

    @FXML
    private ListView<String> usersList;
    @FXML
    private Label emailList;
    String[] users = {"email", "name", "phone_number"};
    String currentUser;
    String currentItem;
    userInterface fn = new userservice();
    user test = new user();

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usersList.getItems().addAll(users);
        usersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                currentUser = usersList.getSelectionModel().getSelectedItem();
                emailList.setText(currentUser);
                if (usersList.getSelectionModel().getSelectedItem().equals("email")) {

                    fn.getalluser();

                }

            }
        });

    }

}
