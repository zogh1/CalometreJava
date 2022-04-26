/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.PreparedStatement;

import entity.Event;
import interfacee.IServiceEvent;
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

    public int getNombre_participants_byevent(int event_id) {
        String req = "select count(*) from event_user where event_id=?;";
        int numberROW = 0;
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);

            ps.setInt(1, event_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                numberROW = rs.getInt("count(*)");
            }

        } catch (SQLException ex) {
            //The printStackTrace() method in Java is a tool used to handle exceptions and errors 
            ex.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }

        return numberROW;
    }

    public boolean user_is_applyed_toevent(int idUser, int idEvent) {
        String req = "select count(*) from event_user where event_id=? and user_id = ?;";
        int numberROW = 0;
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);

            ps.setInt(1, idEvent);
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                numberROW = rs.getInt("count(*)");
            }

        } catch (SQLException ex) {
            //The printStackTrace() method in Java is a tool used to handle exceptions and errors 
            ex.printStackTrace();
            System.out.println("erreur dans la methode insert");
        }

        return numberROW > 0;
    }

    public void applyToEvent(int idUser, int idEvent) {
        Event e = this.getEventById(idEvent);

        if (e != null) {
            if ((e.getNombre_participants() > 0)
                    && (e.getNombre_participants() - this.getNombre_participants_byevent(idEvent) > 0)
                    && (user_is_applyed_toevent(idUser, idEvent) == false)) {
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
                    System.out.println(ex.getMessage());
                }
            } else {
                if ((e.getNombre_participants() == 0)
                        || (e.getNombre_participants() - this.getNombre_participants_byevent(idEvent) == 0)) {
                    System.out.println("Il n'y a plus de places !");
                }
                if (user_is_applyed_toevent(idUser, idEvent) == true) {
                    System.out.println("L'utilisateur est déjà participé à cet évenement !");
                }
            }

        } else {
            System.out.println("Veuillez verifier l'id evenement ou l'idUser ");
        }
    }

    public List<Event> getTopThreeEvents() {
        List<Event> le = new ArrayList<Event>();
        String req = "select * \n"
                + "from event \n"
                + "where id in (select topThreeEventsIds.event_id \n"
                + "             from (select eu.event_id,e.nombre_participants, count(eu.event_id) \n"
                + "                   from event_user eu join event e on eu.event_id=e.id\n"
                + "                   GROUP by eu.event_id,e.nombre_participants\n"
                + "                   order by count(eu.event_id) / e.nombre_participants desc\n"
                + "                   LIMIT 3) as topThreeEventsIds);";
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
}