/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.Reclamation;
import java.util.List;

/**
 *
 * @author RYM BACCOURI
 */
public interface IServiceReclamation {

    public void createReclamation(Reclamation R);

    public void editReclamation(Reclamation R);

    public boolean deleteReclamation(int id);
     public Reclamation getById(int id);
      public List<Reclamation> RechercherparEmail(String email) ;

    public List<Reclamation> readReclamation();
}
