/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevice;

import interfaces.AlimentInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aliment;
import util.MaConnexion;

/**
 *
 * @author admin
 */
public class AlimentService implements AlimentInterface {
    
    //var
    MaConnexion instance = MaConnexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void addAliment(Aliment p) {
        
        //request
        String req = "INSERT INTO `aliments`(`calories`, `name`, `categorie`, `image`) VALUES (?,?,?,?)";
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
            String req = "SELECT * FROM aliments";
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
         
        String req = "delete from aliments where id=?";
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
          

        String req = "update aliments set name=?,calories=?,categorie=?,image=? where id=?";
        try {
            System.out.println("jib " + id);
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
    
    
    
    
    
    
    
    
    
}