/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfacee.AlimentInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Aliment;
import util.connexion;

/**
 *
 * @author admin
 */
public class AlimentService implements AlimentInterface {
    
    //var
    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void addAliment(Aliment p) {
        
        //request
        String req = "INSERT INTO `aliment`(`calories`, `name`, `categorie`, `image`) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setFloat(1, p.getCalories());
            st.setString(2, p.getNom());
            st.setString(3, p.getCategorie());
            st.setString(4, p.getImage());
            st.executeUpdate();
            System.out.println("Aliment ajoutée avec succes.");
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public List<Aliment> fetchAllAliment() {
        ArrayList<Aliment> aliments = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM aliment";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
    }

    @Override
    public void DeleatAliment(int id ) {
         
        String req = "delete from aliment where id=?";
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
    public void UpdateAliment(Aliment a, int id) {
          

        String req = "update aliment set name=?,calories=?,categorie=?,image=? where id=?";
        try {
          
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getNom());
            ps.setFloat(2, a.getCalories());
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
    public List<Aliment> OrderAlimentsByCaloriesASC() {
           ArrayList<Aliment> aliments = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `aliment` ORDER BY calories ASC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
      
    
    
    
    
    
    
    
    
    
}

    @Override
    public List<Aliment> OrderAlimentsByCaloriesDESC() {
         ArrayList<Aliment> aliments = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `aliment` ORDER BY calories DESC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
   
    }

    @Override
    public List<Aliment> OrderByNameASC() {
        ArrayList<Aliment> aliments = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `aliment` ORDER BY name ASC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
    }

    @Override
    public List<Aliment> OrderbyCategorie() {
        ArrayList<Aliment> aliments = new ArrayList();
        
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM `aliment` ORDER BY categorie ASC;";
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
    }


   

    @Override
    public Aliment GetById(int id ) {
              ArrayList<Aliment> aliments = new ArrayList();
              Aliment a=new Aliment();
          String req = "SELECT * FROM `aliment` WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
              while (rs.next()) {      
            
             a = new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image"));
              }
            }
            
         catch (SQLException ex) {
          ex.printStackTrace();
        }
        return a ;
       
    } 

    @Override
    public Aliment GetByName(String name) {
        
              ArrayList<Aliment> aliments = new ArrayList();
              Aliment a=new Aliment();
          String req = "SELECT * FROM `aliment` WHERE name=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
              while (rs.next()) {      
            
             a = new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image"));
              }
            }
            
         catch (SQLException ex) {
          ex.printStackTrace();
        }
        return a ;
       
        
    }

    @Override
    public List<Aliment> ListByCategorie(String categorie) {
       
        ArrayList<Aliment> aliments = new ArrayList();
          String req = "SELECT * FROM `aliment` where categorie=?;";
        
        try {
             PreparedStatement ps = cnx.prepareStatement(req);
          
             ps.setString(1, categorie);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
        
    }

    @Override
    public List<Aliment> ListByCaloriesRange(int bornemin, int bornemax) {
         ArrayList<Aliment> aliments = new ArrayList();
          String req = "SELECT * FROM `aliment` WHERE calories BETWEEN ? AND ?;";
        
        try {
             PreparedStatement ps = cnx.prepareStatement(req);
          
             ps.setInt(1, bornemin);
              ps.setInt(2, bornemax);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                
                aliments.add(new Aliment(rs.getInt("id"), rs.getString("name"), rs.getFloat("calories"), rs.getString("categorie"), rs.getString("image")));
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return aliments;
    }
    
    
    
    
    
    
    
    
    
    
    
}


