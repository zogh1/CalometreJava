/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.Reclamation;
import entity.Reponse;
import java.util.List;

/**
 *
 * @author RYM BACCOURI
 */
public interface IServiceReponse {
    
    public void createReponse(Reponse Rp,Reclamation rec) ;
    
    public void editReponse(Reponse Rp);

    public boolean deleteReponse(int id);

    public List<Reponse> readReponse();
    
}
