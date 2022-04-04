/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfacee.userrservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.user;
import util.connexion;

/**
 *
 * @author Souhail
 */
public class userservice {

    //var
    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    public void createuser(user u) {

        //request
        try {
            String req = "INSERT INTO `user`(`password`, `email`, `roles`, `is_verified`, `firstname`, `lastname`, `phonenumber`, `profile_picture`, `isbanned`, `country_code`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getPassword());
            st.setString(2, u.getEmail());
            st.setString(3, u.getRoles());
            st.setBoolean(4, false);
            st.setString(5, u.getFirstname());
            st.setString(6, u.getLastname());
            st.setInt(7, u.getPhonenumber());
            st.setString(8, u.getProfile_picture());
            st.setBoolean(9, false);
            st.setString(10, u.getCountry_code());
            st.executeUpdate();
            System.out.println("Personne ajoutée avec succes.");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

    }

    public List<user> getalluser() {
        List<user> li = new ArrayList<user>();

        try {
            String req = "select * from user";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                user l = new user();
                l.setId(rs.getInt("id"));
                l.setEmail(rs.getString("email"));
                l.setRoles(rs.getString("roles"));
                l.setIsVerfied(rs.getBoolean("is_verified"));
                l.setFirstname(rs.getString("firstname"));
                l.setLastname(rs.getString("lastname"));
                l.setPhonenumber(rs.getInt("phonenumber"));
                l.setProfile_picture(rs.getString("profile_picture"));
                l.setIsbanned(rs.getBoolean("isbanned"));
                l.setCountry_code(rs.getString("country_code"));

                li.add(l);
            }
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getEmail());
                System.out.println(li.get(i).getFirstname());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;
    }

    public void deleteuser(int id) {

        try {
            String req = "delete from user where id=?";
            PreparedStatement st = connexion.getInstance().getCnx().prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("user delete error");
            ex.printStackTrace();
        }
    }

    public void updateuser(user u) {

        String req = "UPDATE `user` SET `password`='" + u.getPassword() + "',`email`='" + u.getEmail() + "',`roles`='" + u.getRoles() + "',`is_verified`=0,`firstname`='" + u.getFirstname() + "',`lastname`='" + u.getLastname() + "',`phonenumber`='" + u.getPhonenumber() + "',`profile_picture`='" + u.getProfile_picture() + "',`isbanned`=0,`country_code`='" + u.getCountry_code() + "' WHERE id='" + u.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }
}
