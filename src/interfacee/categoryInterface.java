/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.category;
import java.util.List;

/**
 *
 * @author seifd
 */
public interface categoryInterface {

     public boolean createcategory(category c);

    public List<category> getallcategory();

    public void deletecategory(int id);

    public void updatecategory(category c);
    
    public category findById(int id);
    public category findByName(String name);
    public category findCatByName(String name);

}
