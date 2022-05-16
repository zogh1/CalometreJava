/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cart;
import entity.CartItem;
import entity.category;
import entity.product;
import interfacee.ICart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BCrypt;
import util.connexion;

/**
 *
 * @author seifd
 */
public class cartService implements ICart {

    private static cartService instance;
    connexion db = connexion.getInstance();
    Connection cnx = db.getCnx();
    productservice test = new productservice();
    product prod = new product();
     categoryservice cs = new categoryservice();
    public void cartService() {

    }

    public static cartService getInstance() {
        if (cartService.instance == null) {
            cartService.instance = new cartService();
        }
        return cartService.instance;
    }

    @Override
    public Cart findById(long id) {
        
        String req = "SELECT * FROM `cart` where id = ?";
        PreparedStatement st;
        try {
            st = cnx.prepareStatement(req);
            st.setLong(1, id);
            ResultSet result = st.executeQuery();
            result.next();
            Cart cart = new Cart();
            cart.setId(result.getInt("id"));
            cart.setUser_cart_id(result.getInt("user_cart_id"));
            cart.setItems(this.loadProductsFromCart(cart.getId()));
            return cart;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {

        String req = "delete FROM `cart_prods` where idcart_id = ?";
        PreparedStatement st;
        try {
            st = cnx.prepareStatement(req);
            st.setLong(1, id);
            st.execute();
        } catch (SQLException ex) {

        }

        req = "delete FROM `cart` where id = ?";

        try {
            st = cnx.prepareStatement(req);
            st.setLong(1, id);
            st.execute();
        } catch (SQLException ex) {

        }

    }

    @Override
    public void addProduct(product product, long cartId, int quantity) throws Exception {
        this.addProduct(product.getId(), cartId, quantity);
    }

    @Override
    public void addProduct(int pid, long cartId, int quantity) throws Exception {
        Cart cart = this.findById(cartId);
        if (cart == null) {
            throw new Exception("Cart Not Found");
        }
        CartItem item = null;
        //check if product alreadt exists in the cart
        for (CartItem citem : cart.getItems()) {
            if (citem.getProduct().getId() == pid) {
                item = citem;
                break;
            }
        }
        if (item != null) {
            //product already exists; Increasing quantity.

            String req = "update `cart_prods` set qty = qty + ? where idprod_id = ? and idcart_id = ?";

            try {
                PreparedStatement st;
                st = cnx.prepareStatement(req);
                st.setInt(1, quantity);
                st.setInt(2, item.getProduct().getId());
                st.setInt(3, cart.getId());
                st.executeUpdate();
            } catch (SQLException sq) {

            }
        } else {
            //Item doesn't exist; Create new item line.
            String req = "INSERT INTO `cart_prods` (`idprod_id`, `idcart_id`, `qty`) VALUES ( ?, ?, ?);";
            try {
                PreparedStatement st;
                st = cnx.prepareStatement(req);
                st.setInt(1, pid);
                st.setInt(2, cart.getId());
                st.setInt(3, quantity);
                st.execute();

            } catch (SQLException sq) {

            }

        }

    }

    @Override
    public void removeProduct(int pid, int cartId) {
        String req = "Delete from cart_prods where idprod_id = ? and idcart_id = ?";
        try {
            PreparedStatement st;
            st = cnx.prepareStatement(req);
            st.setInt(1, pid);
            st.setInt(2, cartId);
            st.execute();
        } catch (SQLException sq) {

        }

    }

    @Override
    public void removeProduct(product product, int cartId) {
        this.removeProduct(product.getId(), cartId);
    }

    @Override
    public List<Cart> findCartsByUserId(int uid) {
        String req = "Select * from cart where user_cart_id = ? ";
        PreparedStatement st;
        ArrayList<Cart> carts = new ArrayList<>();
        try {

            st = cnx.prepareStatement(req);
            st.setLong(1, uid);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                Cart cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setUser_cart_id(result.getInt("user_cart_id"));
                cart.setItems(this.loadProductsFromCart(cart.getId()));
                carts.add(cart);
            }

            return carts;

        } catch (SQLException ex) {
            return null;
        }

    }

    @Override
    public void createCart(int cid, int tot, int uid) throws Exception {
        int count = 0;

        try {
            String req1 = "SELECT *,COUNT(*) FROM cart";
            PreparedStatement ps = cnx.prepareStatement(req1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (count == 0) {
                    String req = "INSERT INTO `cart` (`id`,`total`, `user_cart_id`) VALUES (?, ?, ?);";
                    try {
                        PreparedStatement st;
                        st = cnx.prepareStatement(req);
                        st.setInt(1, cid);
                        st.setInt(2, tot);
                        st.setInt(3, uid);
                        st.execute();

                    } catch (SQLException sq) {

                    }
                }
            }
        } catch (SQLException e) {
        }

    }

    public ArrayList<CartItem> loadProductsFromCart(int id) {
        String req = "select * from product p, cart_prods cp where p.id = cp.idprod_id and cp.idcart_id = ?";
        PreparedStatement st;
        ArrayList<CartItem> pl = new ArrayList<CartItem>();
        try {
            st = cnx.prepareStatement(req);
            st.setLong(1, id);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                CartItem cartItem = new CartItem();
                product prod = new product();
                prod.setId(result.getInt("id"));
                prod.setName(result.getString("name"));
                prod.setPrice(result.getDouble("price"));
                prod.setDescription(result.getString("description"));
                prod.setQuantity(result.getInt("quantity"));
                prod.setImage(result.getString("image"));

                cartItem.setProduct(prod);
                cartItem.setQuantity(result.getInt("qty"));
                pl.add(cartItem);
            }
        } catch (SQLException ex) {

        }
        return pl;
    }
    
    
    @Override
    public ArrayList<CartItem> getstatinfo() {
        ArrayList<CartItem> cartitems = new ArrayList();
        try {
            String req = "SELECT product.name,cart_prods.qty FROM product INNER JOIN cart_prods ON product.id = cart_prods.idprod_id ORDER by cart_prods.qty DESC;";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                cartitems.add(new CartItem(
                        result.getInt(1),
                        findById(result.getInt(2)),
                        test.findByNameProd(result.getString(3)),
                        result.getInt(1)
                ));
                /* for (int i = 0; i < prods.size(); i++) {
                    System.out.println(prods.get(i).getName());
                    System.out.println(prods.get(i).getCount());
                    System.err.println("*********");
                }*/

            }

        } catch (SQLException ex) {
        }
        return cartitems;
    }

    @Override
    public HashMap<String, Integer> getProductStats() {
        List<CartItem> list = this.getstatinfo();
        HashMap<String, Integer> stat = new HashMap<>();

        for (CartItem ci : list) {
            stat.put(ci.getProduct().getName(), 0);
        }

        list.stream().map((CartItem) -> CartItem.getQuantity()).forEachOrdered((cartitems) -> {
            list.stream().filter((ci) -> (cartitems != 0)).forEachOrdered((ci) -> {
                stat.put(ci.getProduct().getName(), stat.get(ci.getProduct().getName()) + 1);
            });
        });

        return stat;
    }


}
