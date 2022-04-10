/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.category;
import entity.product;
import java.util.List;

/**
 *
 * @author seifd
 */
public interface productInterface {

    public void createproduct(product p, category cat);

    public List<product> getallproduct();

    public void deleteproduct(int id);

    public void updateproduct(product p);
}
