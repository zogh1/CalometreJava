/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.user;
import interfacee.userInterface;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.userservice;

/**
 *
 * @author Souhail
 */
public class UsersListController implements Initializable {

    userInterface fn = new userservice();
    user test = new user();
    user selecteduser = null;

    @FXML
    TableView<user> usersList;
    @FXML
    private TableColumn<user, Integer> userId;
    @FXML
    private TableColumn<user, String> userFirstName;

    @FXML
    private TableColumn<user, String> userLastName;

    @FXML
    private TableColumn<user, String> userRole;

    @FXML

    private TableColumn<user, Integer> userCountryCode;

    @FXML
    private TableColumn<user, Integer> userPhoneNumber;
    @FXML
    private TableColumn<user, Boolean> userBanStatus;

    ObservableList<user> oblist = FXCollections.observableArrayList();

    @FXML
    private void banselectedUser() {
        selecteduser = usersList.getSelectionModel().getSelectedItem();
        fn.banUser(selecteduser.getId());
    }

    @FXML
    private void unbanselectedUser() {
        selecteduser = usersList.getSelectionModel().getSelectedItem();
        fn.unbanUser(selecteduser.getId());
    }

    private void userList() {

        // TODO
        List<user> li = fn.getalluser();

        li.forEach(e -> {
            oblist.add(e);

            userId.setCellValueFactory(new PropertyValueFactory<>("id"));
            userFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            userLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            userRole.setCellValueFactory(new PropertyValueFactory<>("email"));
            userCountryCode.setCellValueFactory(new PropertyValueFactory<>("country_code"));
            userPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
            userBanStatus.setCellValueFactory(new PropertyValueFactory<>("isbanned"));

        });

//
        usersList.setItems(oblist);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userList();
    }

}
