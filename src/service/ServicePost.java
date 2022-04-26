/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Event;
import entity.Post;
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
 * @author wassim
 */
public class ServicePost {
     connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    public List<Post> getallPost() {
        List<Post> le = new ArrayList<Post>();
        String req = "select * from post";
        try {
            Statement s = connexion.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                ServiceEvent se = new ServiceEvent();

                Event ev = se.getEventById(rs.getInt("event_id"));

                p.setEv(ev);

                p.setName(rs.getString("name"));

                p.setCreation_date(rs.getString("creation_date"));
                p.setDescription(rs.getString("description"));

                System.out.println(p);

                le.add(p);
            }

        } catch (SQLException err) {
            System.out.println("erreur getall post");
        }

        return le;
    }
    public Post getPostById(int id) {
        Post p = new Post();
        String req = "select * from post where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                p.setId(rs.getInt("id"));
			
                p.setName(rs.getString("nom"));
                p.setCreation_date(rs.getString("creation_date"));
        
                p.setDescription(rs.getString("description"));
              
            }

        } catch (SQLException err) {
            System.out.println("sala7  getpostby id");
            err.printStackTrace();
        }
        System.out.println(p);
        return p;
   
}   
    public void createPost(Post p ) {

        //req BD
        String req = "INSERT INTO `post`(`name`,`event_id`, `creation_date`, `description`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
             // PreparedStatement définit les méthodes pour un objet qui va encapsuler une requête précompilée
            ps.setString(1, p.getName());
            ps.setString(3, p.getCreation_date());
         
            ps.setString(4, p.getDescription());
          
            ps.setInt(2, p.getEv().getId());
            ps.executeUpdate();
            System.out.println("Post added .");

        } catch (SQLException ex) {
            //The printStackTrace() method in Java is a tool used to handle exceptions and errors 
            ex.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }

    }
    public void deletePost(int id) {
        String req = "delete from post where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException er) {
            System.out.println("delete erreur post");
            er.printStackTrace();
        }
    }
    public void updatePost(Post p , int id) {

        String req = "update post set name=?,creation_date	=?,description=? where id=?";
        try {
            System.out.println("jib" + id);
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCreation_date());
      
            ps.setString(3, p.getDescription());
           
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            System.out.println("erreur dans la methode update");
        }
    }
}
