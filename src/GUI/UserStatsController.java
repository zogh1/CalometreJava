/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfacee.userInterface;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author Souhail
 */
public class UserStatsController implements Initializable {

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

}
