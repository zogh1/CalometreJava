/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reclamation;
import interfacee.IServiceReponse;
import entity.Reponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import util.Connexion;

/**
 *
 * @author RYM BACCOURI
 */
public class ServiceReponse implements IServiceReponse {

    Connexion instance = Connexion.getInstance();
    Connection cnx = instance.getCnx();

    /**
     *
     * @param Rp
     * @param rec
     */
    @Override
    public void createReponse(Reponse Rp, Reclamation rec) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        try {
            String req = "INSERT INTO `reponse`(`date`,`repondre_id`,`reponse`) VALUES (?,?,?);";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, dt.format(now));
            st.setInt(2, rec.getId());
            st.setString(3, Rp.getReponse());
            st.executeUpdate();

            System.out.println("Reponse ajoutée avec succes.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void editReponse(Reponse Rp) {
        try {
            String req = "update reponse set reponse = '" + Rp.getReponse() + "' WHERE id='" + Rp.getId() + "'";
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate(req);
            System.out.println("Reponse modifié avec succes.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean deleteReponse(int id) {
        boolean isDeleted = false;
        try {
            String req = "delete from reponse where id = ?";
            PreparedStatement prepare = cnx.prepareStatement(req);
            prepare.setInt(1, id);
            prepare.executeUpdate();
            isDeleted = true;
            System.out.println("reponse deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return isDeleted;
    }
    
    @Override
    public List<Reponse> readReponse() {
        ArrayList<Reponse> reponse = new ArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM reponse";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                reponse.add(new Reponse(rs.getInt(1), rs.getString("date"), rs.getInt("repondre_id"), rs.getString("reponse")));
            }

            for (int i = 0; i < reponse.size(); i++) {
                System.out.println("*********");
                System.out.println(reponse.get(i).getId());
                System.out.println(reponse.get(i).getReponse());
            }

        } catch (SQLException ex) {
        }

        return reponse;

    }

}
