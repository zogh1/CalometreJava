/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Reclamation;
import entity.Reponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceReclamation;
import service.ServiceReponse;

/**
 * FXML Controller class
 *
 * @author RYM BACCOURI
 */
public class ReponseController implements Initializable {

    @FXML
    private TextField idf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField datef;
       @FXML
    private TextField repf;
    @FXML
    private Button addreponse;
     @FXML
    private Button editrep;

     @FXML
    private Button deleterep;
     
      ServiceReponse f = new ServiceReponse();
      Reponse rep = new Reponse();
      
ServiceReclamation fn = new ServiceReclamation();
    Reclamation rec = new Reclamation();
        private void ajouterreponse(){ 
  
    String mail=emailf.getText();
    String repondre=repf.getText();
   
   /* if (mail.isEmpty()  || type.isEmpty() || message.isEmpty()|| date.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("null");
            alert.setContentText("Please fill all required fields");
            alert.showAndWait();
        }
    else{*/
    
    
         
        rep.setReponse(repondre);
        

       f.createReponse(rep,rec);
      
    
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
