/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.PreparedStatement;

import entity.Event;
import java.sql.Connection;

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
public class ServiceEvent {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    public List<Event> getallEvent() {
        List<Event> le = new ArrayList<Event>();
        String req = "select * from event";
        try {
            Statement s = connexion.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDate_debut(rs.getString("date_debut"));
                e.setDate_fin(rs.getString("date_fin"));
                e.setDescription(rs.getString("description"));
                e.setNombre_participants(rs.getInt("nombre_participants"));
                e.setLieu(rs.getString("lieu"));
                e.setImage(rs.getString("image"));
                System.out.println(e);

                le.add(e);
            }

        } catch (SQLException err) {
            System.out.println("erreur getall envenement");
        }

        return le;
    }

    public Event getEventById(int id) {
        Event e = new Event();
        String req = "select * from event where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDate_debut(rs.getString("Date_debut"));
                e.setDate_fin(rs.getString("Date_fin"));
                e.setDescription(rs.getString("description"));
                e.setNombre_participants(rs.getInt("Nombre_participants"));
                e.setLieu(rs.getString("lieu"));
                e.setImage(rs.getString("image"));
            }

        } catch (SQLException err) {
            System.out.println("sala7  geteventby id");
            err.printStackTrace();
        }
        System.out.println(e);
        return e;
    }

    public void createEvent(Event e) {

        //req BD
        String req = "INSERT INTO `event`(`nom`, `date_debut`, `date_fin`, `description`, `nombre_participants`, `lieu`, `image`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);

            ps.setString(1, e.getNom());
            ps.setString(2, e.getDate_debut());
            ps.setString(3, e.getDate_fin());
            ps.setString(4, e.getDescription());
            ps.setInt(5, e.getNombre_participants());
            ps.setString(6, e.getLieu());
            ps.setString(7, e.getImage());
            
            ps.executeUpdate();
            System.out.println("Event added .");

        } catch (SQLException ex) {
            //The printStackTrace() method in Java is a tool used to handle exceptions and errors 
            ex.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }

    }

    public void deleteEvent(int id) {
        String req = "delete from event where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException er) {
            System.out.println("delete erreur event");
            er.printStackTrace();
        }
    }

    public void updateEvent(Event e, int id) {

        String req = "update event set nom=?,date_debut=?,date_fin=?,description=?,nombre_participants=?,lieu=?,image=? where id=?";
        try {
            System.out.println("jib" + id);
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDate_debut());
            ps.setString(3, e.getDate_fin());
            ps.setString(4, e.getDescription());
            ps.setInt(5, e.getNombre_participants());
            ps.setString(6, e.getLieu());
            ps.setString(7, e.getImage());
            ps.setInt(8, id);

            ps.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            System.out.println("erreur dans la methode update");
        }
    }

<<<<<<< HEAD
    
    
    public void applyToEvent(int idUser,int idEvent) {
            ServiceEvent se = new ServiceEvent();
            Event e = se.getEventById(idEvent);
            
            if(e!= null){
                if(e.getNombre_participants()>0){
                     String req2 = "INSERT INTO `event_user`(`event_id`, `user_id`) VALUES (?,?)";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req2);

            ps.setInt(1, idEvent);
            ps.setInt(2, idUser);
            
            ps.executeUpdate();
            System.out.println("Applied to event .");
            
    

        } catch (SQLException ex) {
            //The printStackTrace() method in Java is a tool used to handle exceptions and errors 
            ex.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }
                    int nbPArticipants = e.getNombre_participants() -1 ;
                     String req = "update event set nombre_participants=? where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, nbPArticipants);
            ps.setInt(2, idEvent);
            ps.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            System.out.println("erreur dans la methode update nombre participants");
        }
                    
                    
                }
                else {
                    System.out.println("il n'y a plus de places");
                }
               
        }else {
                System.out.println("Veuillez verifier l'id evenement ou l'idUser ");
            }
    }
=======
>>>>>>> reclamationjava
}
