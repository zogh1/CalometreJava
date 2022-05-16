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
import interfacee.productInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import javafx.beans.InvalidationListener;
import util.connexion;

/**
 *
 * @author seifd
 */
public class productservice implements productInterface {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();
    
    categoryservice cs = new categoryservice();
   

    public product findById(int id) {
        product u = null;
        try {
            String req = "select * from product where id=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        cs.findById(rs.getInt("category_id")),
                        rs.getString("image")
                );
            }

        } catch (Exception e) {
        }
        return u;

    }

    public List<String> getQrCodeInfo() {
        int count = 0;
        String prodName = "";
        ArrayList<String> list = new ArrayList();
        try {
            String req = "SELECT *,COUNT(*) FROM cart_prods WHERE qty IN (SELECT MAX(qty) FROM cart_prods)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt("qty");
                if (count != 0) {
                    prodName = this.findById(rs.getInt(2)).getName();

                }
            }

            list.add(prodName);
            list.add(String.valueOf(count));

        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public boolean createproduct(product p, category cat) {
        boolean created = false;
        //request
        try {
            String req = "INSERT INTO `product`(`name`, `price`, `description`, `quantity`, `category_id`, `image`) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getName());
            st.setDouble(2, p.getPrice());
            st.setString(3, p.getDescription());
            st.setInt(4, p.getQuantity());
            st.setInt(5, cat.getId());
            st.setString(6, p.getImage());
            st.executeUpdate();
            System.out.println("Produit ajoutée avec succes.");
            created = true;

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return created;
    }

    @Override
    public List<product> getallproduct() {
        List<product> li = new ArrayList<product>();

        try {
            String req = "select * from product";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                product p = new product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setCategory_id(cs.findById(rs.getInt("category_id")));
                p.setImage(rs.getString("image"));

                li.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;
    }

    @Override
    public List<product> paginationProd() {
        List<product> li = new ArrayList<product>();

        try {
            String req = "select * from product LIMIT 1 OFFSET 0 ";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                product p = new product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImage(rs.getString("image"));

                li.add(p);
            }

        } catch (SQLException ex) {
        }

        return li;
    }

    public List<product> searchByCategory(int catId) {
        List<product> li = new ArrayList();

        try {
            String req = "SELECT * FROM product p WHERE p.category_id ='" + catId + "'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                product p = new product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setCategory_id(cs.findById(rs.getInt("category_id")));
                p.setImage(rs.getString("image"));

                li.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;
    }

    @Override
    public List<product> searchProduct(String search) {
        List<product> li = new ArrayList();

        try {
            String req = "SELECT * FROM product WHERE name LIKE '%" + search + "%'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                product p = new product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImage(rs.getString("image"));
                p.setCategory_id(cs.findById(rs.getInt("category_id")));

                li.add(p);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;

    }

    @Override
    public void deleteproduct(int id) {

        try {
            String req = "delete from product where id=?";
            PreparedStatement st = cnx.prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("user delete error");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean quantity(product p) {
        boolean qtydone = false;
        String req = "UPDATE `product` SET `quantity`='" + p.getQuantity() + "' WHERE id='" + p.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
            qtydone = true;
        } catch (SQLException e) {
            System.out.println("error");
        }
        return qtydone;
    }

    @Override
    public boolean updateproduct(product p, category cat) {
        boolean done = false;
        String req = "UPDATE `product` SET `name`='" + p.getName() + "',`price`='" + p.getPrice() + "',`description`='" + p.getDescription() + "',`quantity`='" + p.getQuantity() + "',`image`='" + p.getImage() + "' ,`category_id`='" + cat.getId() + "' WHERE id='" + p.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
            done = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
        return done;
    }

    @Override
    public List<product> searchByCategory(String cat) {
        int id = cs.findCatByName(cat).getId();
        System.out.println(cs.findCatByName(cat).getName());
        System.out.println(id);

        List<product> li = new ArrayList();
        try {
            String req = "SELECT * FROM product Where category_id=" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                product p = new product();
                p.setId(result.getInt("id"));
                p.setName(result.getString("name"));
                p.setPrice(result.getDouble("price"));
                p.setDescription(result.getString("description"));
                p.setQuantity(result.getInt("quantity"));
                p.setImage(result.getString("image"));
                p.setCategory_id(cs.findById(result.getInt("category_id")));

                li.add(p);

                System.out.println(p.getName());
            }

        } catch (SQLException ex) {
        }
        return li;
    }

    @Override
    public ArrayList<product> getNumberofproodsByCat() {
        ArrayList<product> prods = new ArrayList();
        try {
            String req = "SELECT id,name,category_id, COUNT(*) FROM product GROUP BY category_id;";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                prods.add(new product(
                        result.getInt(1),
                        result.getString(2),
                        cs.findById(result.getInt(3)),
                        result.getInt(4)
                ));
                /* for (int i = 0; i < prods.size(); i++) {
                    System.out.println(prods.get(i).getName());
                    System.out.println(prods.get(i).getCount());
                    System.err.println("*********");
                }*/

            }

        } catch (SQLException ex) {
        }
        return prods;
    }
 
    @Override
    public product findByNameProd(String name) {
        product u = null;
        try {
            String req = "SELECT id FROM product WHERE name=?";
            
          PreparedStatement st = cnx.prepareStatement(req);
          st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                u = new product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        cs.findById(rs.getInt("category_id")),
                        rs.getString("image")
                );
            }

        } catch (Exception e) {
        }
        return u;
    }
}
