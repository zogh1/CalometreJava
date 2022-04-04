/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Reclamation;
import entity.Reponse;
import service.ServiceReclamation;
import service.ServiceReponse;
import util.Connexion;

/**
 *
 * @author RYM BACCOURI
 */
public class Calometre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application lnogic here
        //ServiceReclamation fn = new ServiceReclamation();
//        Reclamation rec = new Reclamation();
        ServiceReponse f = new ServiceReponse();
        Reponse rep = new Reponse();
        Reclamation rec = new Reclamation();

        //ajout
        /* rec.setEmail("rymm");
        rec.setMessage("mehjgucugcssage");
        rec.setType("coach");

        fn.createReclamation(rec);*/
        //edit
        /* rec.setId(39);
        rec.setEmail("test");
        rec.setMessage("message");
        rec.setType("ttttttt");

        fn.editReclamation(rec);*/
        //affichage
        //fn.readReclamation();
        // rec.setId(36);
        /* rec.setId(36);
       fn.deleteReclamation(35);
         */
        //delete 
        //ajout
        
        rep.setReponse("rymm");

        rep.setId(52);
        
        f.editReponse(rep);

    }

}
