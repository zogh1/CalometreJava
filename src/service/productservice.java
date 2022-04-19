/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import util.connexion;

/**
 *
 * @author seifd
 */
public class productservice implements productInterface {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    categoryservice cs = new categoryservice();

    @Override
    public void createproduct(product p, category cat) {

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

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

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
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getPrice());
                System.out.println(li.get(i).getDescription());
                System.out.println(li.get(i).getQuantity());
                System.out.println(li.get(i).getImage());

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
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getPrice());
                System.out.println(li.get(i).getDescription());
                System.out.println(li.get(i).getQuantity());
                System.out.println(li.get(i).getImage());

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
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getPrice());
                System.out.println(li.get(i).getDescription());
                System.out.println(li.get(i).getQuantity());
                System.out.println(li.get(i).getImage());
            }

        } catch (SQLException ex) {
        }

        return li;
    }

    @Override
    public List<product> searchProduct(String search) {
        List<product> li = new ArrayList();

        try {
            String req = "SELECT * FROM product WHERE name LIKE '%" + search + "%' OR price LIKE '%" + search + "%' OR description LIKE '%" + search + "%'";
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
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getPrice());
                System.out.println(li.get(i).getDescription());
                System.out.println(li.get(i).getQuantity());
                System.out.println(li.get(i).getImage());
            }

        } catch (SQLException ex) {
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
    public void updateproduct(product p) {

        String req = "UPDATE `product` SET `name`='" + p.getName() + "',`price`='" + p.getPrice() + "',`description`='" + p.getDescription() + "',`quantity`='" + p.getQuantity() + "',`image`='" + p.getImage() + "' WHERE id='" + p.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
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
    public HashMap<String, Integer> getProductStats() {
        List<product> list = this.getNumberofproodsByCat();
        HashMap<String, Integer> stat = new HashMap<>();

        for (product ps : list) {
            stat.put(ps.getCategory_id().getName(), 0);
        }

        list.stream().map((product) -> product.getCount()).forEachOrdered((count) -> {
            list.stream().filter((ps) -> (count != 0)).forEachOrdered((ps) -> {
                stat.put(ps.getCategory_id().getName(), stat.get(ps.getCategory_id().getName()) + 1);
            });
        });

        return stat;
    }

    public void createproduct(category cat, product test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
