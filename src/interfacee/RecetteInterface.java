/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import java.util.List;
import model.Recette;

/**
 *
 * @author Ahmed Mahjoub
 */
public interface RecetteInterface {
     public void addRecette(Recette a);
    public List<Recette> fetchAllRecette();
    public void DeleatRecette(int id);
     public void UpdateRecette(Recette a ,int id);
    
}
