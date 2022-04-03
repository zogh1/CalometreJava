/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Reclamation;
import service.ServiceReclamation;
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
        ServiceReclamation fn = new ServiceReclamation();
        Reclamation rec = new Reclamation();

        //ajout
       /* rec.setEmail("test");
        rec.setMessage("message");
        rec.setType("ttttttt");

        fn.createReclamation(rec);*/

        //edit
       /* rec.setId(39);
        rec.setEmail("test");
        rec.setMessage("message");
        rec.setType("ttttttt");

        fn.editReclamation(rec);*/

        //affichage
        /*fn.readReclamation();*/
    }

}
