/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import java.util.List;
import entity.category;

/**
 *
 * @author seifd
 */
public interface categoryInterface {

    public void createcategory(category c);

    public List<category> getallcategory();

    public void deletecategory(int id);

    public void updatecategory(category c);

}
