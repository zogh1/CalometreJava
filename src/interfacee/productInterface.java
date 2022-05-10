/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.category;
import entity.product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author seifd
 */
public interface productInterface {
    public ArrayList<product> getNumberofproodsByCat() ;

    public boolean createproduct(product p, category cat);

    public List<product> getallproduct();

    public void deleteproduct(int id);

    public boolean updateproduct(product p, category cat);

    public List<product> paginationProd();

    public List<product> searchProduct(String search);
    
    public HashMap<String, Integer> getProductStats();
}
