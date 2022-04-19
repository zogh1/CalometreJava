/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfacee.IServiceReclamation;
import entity.Reclamation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import util.connexion;
<<<<<<< HEAD
=======
import static util.sendSMS.sendSMS;
import util.session;
>>>>>>> reclamationjava

/**
 *
 * @author RYM BACCOURI
 */
public class ServiceReclamation implements IServiceReclamation {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    public Reclamation getById(int id) {
        Reclamation r = new Reclamation();
        String req = "select * from Reclamation where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setDate(rs.getString("Date"));
                r.setType(rs.getString("TYPE"));
                r.setMessage(rs.getString("message"));

            }

        } catch (SQLException erreur) {
            System.out.println("erreurr");
            erreur.printStackTrace();
        }
        System.out.println(r);
        return r;
    }

    public Reclamation getBytype(String type) {
        Reclamation r = new Reclamation();
        String req = "select * from Reclamation where type=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setDate(rs.getString("Date"));
                r.setType(rs.getString("TYPE"));
                r.setMessage(rs.getString("message"));

            }

        } catch (SQLException erreur) {
            System.out.println("erreurr");
            erreur.printStackTrace();
        }
        System.out.println(r);
        return r;
    }

    @Override
    public void createReclamation(Reclamation R) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String req = "INSERT INTO `reclamation`(`date`,`type`,`message`, `email`) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, dtf.format(now));
            st.setString(2, R.getType());
            st.setString(3, R.getMessage());
            st.setString(4, R.getEmail());

            st.executeUpdate();
            System.out.println("Reclamation ajoutée avec succes.");
            sendSMS(R);

        } catch (SQLException ex) {
        }

    }

    @Override
    public void editReclamation(Reclamation R) {
        try {
            String req = "update reclamation set type = '" + R.getType() + "', message ='" + R.getMessage() + "', email = '" + R.getEmail() + "'   WHERE id='" + R.getId() + "'";
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate(req);
            System.out.println("Reclamation modifié avec succes.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean deleteReclamation(int id) {
        boolean isDeleted = false;
        try {
            String req = "delete from reclamation where id = ?";
            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            isDeleted = true;
            System.out.println("reclamation deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return isDeleted;
    }

    public List<Reclamation> getByUser() {
        ArrayList<Reclamation> reclamationUser = new ArrayList();
        String req1 = "SELECT * FROM reclamation where id_id="+ session.getUser().getId();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req1);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                reclamationUser.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationUser;
    }

    @Override
    public List<Reclamation> readReclamation() {
        ArrayList<Reclamation> reclamation = new ArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM reclamation";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                reclamation.add(new Reclamation(rs.getInt(1), rs.getString("email"), rs.getString("type"), rs.getString("message"), rs.getString("date")));
            }

            for (int i = 0; i < reclamation.size(); i++) {
                System.out.println("*********");
                System.out.println(reclamation.get(i).getEmail());
                System.out.println(reclamation.get(i).getMessage());
            }

        } catch (SQLException ex) {
        }

        return reclamation;

    }

    @Override
    public List<Reclamation> RechercherparEmail(String email) {
        ArrayList<Reclamation> reclamation = new ArrayList();

        String req1 = "SELECT * FROM reclamation where email=?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req1);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                reclamation.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamation;

    }

    public void createReponse(Reclamation rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
