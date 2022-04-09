/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfacee.RecetteInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Recette;
import entity.RecetteLike;
import entity.user;
import util.connexion;

/**
 *
 * @author Ahmed Mahjoub
 */
public class RecetteService implements RecetteInterface {
      //var
    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void addRecette(Recette p) {
        String req = "INSERT INTO `recette`(`regime`, `name`, `categorie`, `image`) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getRegime());
            st.setString(2, p.getNom());
            st.setString(3, p.getCategorie());
            st.setString(4, p.getImage());
            st.executeUpdate();
            System.out.println("Recette ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }
    

    @Override
    public List<Recette> fetchAllRecette() {
       ArrayList<Recette> recettes = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM recette";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                recettes.add(new Recette(rs.getInt("id"), rs.getString("name"), rs.getString("regime"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return recettes;
    }

    

    @Override
    public void DeleatRecette(int id) {
         String req = "delete from recette where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException er) {
            System.out.println("Erreur lors de la suppression");
            er.printStackTrace();
        }
    }

    @Override
    public void UpdateRecette(Recette a, int id) {
        String req = "update recette set name=?,regime=?,categorie=?,image=? where id=?";
        try {
            System.out.println("jib " + id);
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getRegime());
            ps.setString(3, a.getCategorie());
            ps.setString(4, a.getImage());
            ps.setInt(5, id);

            ps.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            System.out.println("erreur dans la methode update");
        }

    
}

    @Override
    public List<Recette> OrderByNameASC() {
         ArrayList<Recette> recettes = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `recette` ORDER BY name ASC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                recettes.add(new Recette(rs.getInt("id"),rs.getString("name"), rs.getString("regime"),rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return recettes;
        
    }

    @Override
    public List<Recette> OrderbyCategorie() {
       
         ArrayList<Recette> recettes = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `recette` ORDER BY categorie ASC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                recettes.add(new Recette(rs.getInt("id"),rs.getString("name"), rs.getString("regime"),rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return recettes;
       
    }

    @Override
    public Recette GetById(int id) {
         ArrayList<Recette> recettes = new ArrayList();
              Recette a =new Recette();
          String req = "SELECT * FROM `recette` WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
              while (rs.next()) {      
            
             a = new Recette(rs.getInt("id"), rs.getString("name"), rs.getString("regime"), rs.getString("categorie"), rs.getString("image"));
              }
            }
            
         catch (SQLException ex) {
          ex.printStackTrace();
        }
        return a ;
        
    }

    @Override
     public Recette GetByName(String name) {
        
              ArrayList<Recette> recettes = new ArrayList();
             Recette a=new Recette();
          String req = "SELECT * FROM `recette` WHERE name=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
              while (rs.next()) {      
            
             a = new Recette(rs.getInt("id"), rs.getString("name"), rs.getString("regime"), rs.getString("categorie"), rs.getString("image"));
              }
            }
            
         catch (SQLException ex) {
          ex.printStackTrace();
        }
        return a ;
        
    }

    @Override
    public List<Recette> ListByCategorie(String categorie) {
       
       
        ArrayList<Recette> recettes = new ArrayList();
          String req = "SELECT * FROM `recette` where categorie=?;";
        
        try {
             PreparedStatement ps = cnx.prepareStatement(req);
          
             ps.setString(1, categorie);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                
                recettes.add(new Recette(rs.getInt("id"), rs.getString("name"), rs.getString("regime"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return recettes;
        
    }

    
