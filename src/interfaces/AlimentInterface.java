/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Aliment;

/**
 *
 * @author Ahmed Mahjoub
 */
public interface AlimentInterface {
    public void addAliment(Aliment a);
    public List<Aliment> fetchAllAliment();
    public void DeleatAliment(int id);
     public void UpdateAliment(Aliment a ,int id);
    
}
