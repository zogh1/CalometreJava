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
public class userservice implements userrservice{
    
    //var
    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void createuser(user u) {
        
        //request
        String req = "INSERT INTO `personne`(`firstname`,`roles`,`isbanned`,`isVerified`, `lastname`, `password`,`phonenumber`,`country_code`,`email`, `profile_picture`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getFirstname());
            st.setString(2, u.getLastname());
            st.setInt(3, u.getAge());
            st.setString(4, u.getCin());
            st.setDate(5, u.getDate());
            st.executeUpdate(req);
            System.out.println("Personne ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }
