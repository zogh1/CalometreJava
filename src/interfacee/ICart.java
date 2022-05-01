/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;
import entity.Cart;
import entity.CartItem;
import entity.product;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author seifd
 */
public interface ICart {
    public Cart findById(long id) ;
    public void deleteById(long id);
    public void addProduct(product product, long cartId, int quantity) throws Exception;
    public void addProduct(int pid, long cartId, int quantity) throws Exception;
    public void removeProduct(int pid, int cid);
    public void removeProduct(product product, int cid);
    public List<Cart> findCartsByUserId(int uid);   
    public ArrayList<CartItem> loadProductsFromCart(int id);
    public void createCart(int cid, int tot, int uid) throws Exception;
    
}