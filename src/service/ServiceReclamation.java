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

/**
 *
 * @author RYM BACCOURI
 */
public class ServiceReclamation implements IServiceReclamation {

    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

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

        } catch (SQLException ex) {
        }

    }

    @Override
    public void editReclamation(Reclamation R) {
        try {
            String req = "update reclamation set type = '" + R.getType() + "', message ='" + R.getMessage() + "', email = '" + R.getEmail() + "' WHERE id='" + R.getId() + "'";
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

    public void createReponse(Reclamation rec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
