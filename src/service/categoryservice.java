/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.category;
import interfacee.categoryInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.connexion;

/**
 *
 * @author seifd
 */
public class categoryservice implements categoryInterface {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public category findById(int id) {
        category u = null;
        try {
            String req = "select * from category where id=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new category(
                        rs.getInt(1),
                        rs.getString(2));
            }

        } catch (Exception e) {
        }
        return u;

    }

    @Override
    public category findByName(String name) {
        category u = null;
        try {
            String req = "SELECT id FROM category WHERE name='" + name + "'";
            System.out.println(req);
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new category(
                        rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public category findCatByName(String name) {
        category u = null;
        try {
            String req = "SELECT id,name FROM category WHERE name='" + name + "'";
            System.out.println(req);
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new category(
                        rs.getInt(1), rs.getString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    /*public category findByName(String name) {
        List<category> li = new ArrayList();

        try {
            String req = "SELECT id FROM category WHERE name= '" + name + "'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                category p = new category();
                 p.setId(rs.getInt("id"));
                 
                li.add(p);
            }
            for (int i = 0; i < li.size(); i++) {
              System.out.println(li.get(i).getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;

    }*/
    @Override
    public boolean createcategory(category c) {
        boolean isadded = false;

        //request
        try {
            String req = "INSERT INTO `category`(`name`) VALUES (?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, c.getName());
            st.executeUpdate();

            isadded = true;

        } catch (SQLException ex) {
        }
        return isadded;

    }

    @Override
    public List<category> getallcategory() {
        List<category> li = new ArrayList<category>();

        try {
            String req = "select * from category";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                category c = new category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                li.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;
    }

    @Override
    public void deletecategory(int id) {

        try {
            String req = "delete from category where id=?";
            PreparedStatement st = connexion.getInstance().getCnx().prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("user delete error");
            ex.printStackTrace();
        }
    }

    @Override
    public void updatecategory(category c) {

        String req = "UPDATE `category` SET `name`='" + c.getName() + "' WHERE id='" + c.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }
}
