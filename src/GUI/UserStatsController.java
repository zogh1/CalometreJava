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
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author Souhail
 */
public class UserStatsController implements Initializable {

    userInterface fn = new userservice();
    user test = new user();
    @FXML
    private Label adminCount;
    @FXML
    private Label clientCount;
    @FXML
    private Label coachCount;
    @FXML
    private Label blockedCount;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void GoToBackProds() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BackOfficeProducts.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    public void GoToCat() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userInterface fn = new userservice();

        // pieChart
        HashMap<String, Integer> stat = fn.getStatTypeOfUsers();

        int numberOfAdmins = stat.get("Admins");
        int numberOfCoaches = stat.get("Coaches");
        int numberOfClients = stat.get("Clients");

//        random data
        adminCount.setText(fn.countuserbyRole("admin") + " Administrateurs");
        clientCount.setText(fn.countuserbyRole("client") + " Clients");
        coachCount.setText(fn.countuserbyRole("coach") + " Coaches");
        blockedCount.setText(fn.countBannedAccounts() + " Comptes bloqués");
    }

    public void logout() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
        fn.logout();

    }

    public void LinkToUsersList() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userslist.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();

    }

}
