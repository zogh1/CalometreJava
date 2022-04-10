/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import java.util.List;
import entity.Recette;
import entity.RecetteLike;
import entity.user;

/**
 *
 * @author Ahmed Mahjoub
 */
public interface RecetteInterface {

    public void addRecette(Recette a);
    public List<Recette> fetchAllRecette();
    public void DeleatRecette(int id);
    public void UpdateRecette(Recette a, int id);
    public List<Recette> OrderByNameASC();
    public List<Recette> OrderbyCategorie();
    public Recette GetById(int id);
    public Recette GetByName(String name);
    public List<Recette> ListByCategorie(String categorie);
    public void Addlike(RecetteLike like);
    public void Removelike(RecetteLike like);
    public RecetteLike Like(user user,Recette recette);
   
    
    
    
    


}
