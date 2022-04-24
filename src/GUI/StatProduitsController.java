/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import calometre.Calometre;
import entity.product;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import service.productservice;

/**
 * FXML Controller class
 *
 * @author seifd
 */
public class StatProduitsController implements Initializable {

    @FXML
    private PieChart pieChart;

    productservice ps = new productservice();

    public void GoToCat() throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addcategory.fxml"));
        Calometre.primaryStage.setScene(new Scene(root));
        Calometre.primaryStage.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<product> list = ps.getNumberofproodsByCat();

        list.stream().map((prod) -> {
            int number = prod.getCount();
            PieChart.Data slice = new PieChart.Data(prod.getCategory_id().getName(), number);
            return slice;
        }).forEachOrdered((slice) -> {
            pieChart.getData().add(slice);
        }); /*stat.entrySet().stream().map((mapentry) -> {
        System.out.println(stat.get(mapentry.getValue()));
        int nb = stat.get(mapentry.getValue());
        PieChart.Data slice = new PieChart.Data(mapentry.getKey(), nb);
        return slice;
        }).forEachOrdered((slice) -> {
        pieChart.getData().add(slice);
        });*/
    }

}
