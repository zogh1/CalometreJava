/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import java.util.List;
import entity.Aliment;

/**
 *
 * @author Ahmed Mahjoub
 */
public interface AlimentInterface {
    public void addAliment(Aliment a);
    public List<Aliment> fetchAllAliment();
    public void DeleatAliment(int id);
    public void UpdateAliment(Aliment a ,int id);
    public List<Aliment> OrderAlimentsByCaloriesASC();
    public List<Aliment> OrderAlimentsByCaloriesDESC();
    public List<Aliment> OrderByNameASC();
    public List<Aliment> OrderbyCategorie();
    public Aliment GetById(int id);
    public Aliment GetByName(String name);
    public List<Aliment> ListByCategorie(String categorie);
    public List<Aliment> ListByCaloriesRange(int bornemin,int bornemax );
     
         
      
     
    
}
