/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.user;
import interfacee.userInterface;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private static int row;
    private static int size = 5;
    private static int nb;

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
    @FXML
    private Button NextPage;
    @FXML
    private Button PreviousPage;

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

    private void userList(int i, int j) {

        int testEndPage = fn.getRowCount() - UsersListController.row;
//        System.out.println("1 : " + UsersListController.row);
//        System.out.println("2 : " + this.size);
//        System.out.println("3 : " + testEndPage);
//        System.out.println("4 : " + fn.getRowCount());
//        System.err.println("*************************");

        if (this.size >= fn.getRowCount() || this.size >= testEndPage) {
            NextPage.setDisable(true);

        } else {
            NextPage.setDisable(false);

        }
        if (UsersListController.row < this.size) {
            PreviousPage.setDisable(true);
        } else {
            PreviousPage.setDisable(false);
        }

        // TODO
        List<user> li = fn.pagination(i, j);

        li.forEach(e
                -> {
            oblist.add(e);

            userId.setCellValueFactory(new PropertyValueFactory<>("id"));
            userFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            userLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            userRole.setCellValueFactory(new PropertyValueFactory<>("email"));
            userCountryCode.setCellValueFactory(new PropertyValueFactory<>("country_code"));
            userPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
            userBanStatus.setCellValueFactory(new PropertyValueFactory<>("isbanned"));

        }
        );

//
        usersList.setItems(oblist);

    }

    @FXML
    private void LinkToNextPage() throws IOException {
        UsersListController.row = UsersListController.row + UsersListController.size;
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

    @FXML
    private void LinkPreviousPage() throws IOException {
        UsersListController.row = UsersListController.row - UsersListController.size;
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

//
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userList(UsersListController.size, UsersListController.row);
    }

}
