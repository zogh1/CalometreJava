/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.exercice;
import entity.typeExercice;
import interfacee.exerciceInterface;
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
 * @author louay
 */
public class exerciceService implements exerciceInterface {

    Connection cnx = connexion.getInstance().getCnx();
    Statement stmt;

    @Override
    public void addExercice(typeExercice type, exercice exc) {
        try {
            String req = "INSERT INTO `exercice`(`nomtype_id`, `nom`, `video`, `description`, `objectif`) VALUES (?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, type.getId());
            st.setString(2, exc.getNom());
            st.setString(3, exc.getVideo());
            st.setString(4, exc.getDescription());
            st.setString(5, exc.getObjectif());
            st.executeUpdate();

            System.out.println("exercice added successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editExercice(exercice exc) {
        try {
            String req = "update exercice set nom='" + exc.getNom() + "',video='" + exc.getVideo() + "',description='" + exc.getDescription() + "',objectif='" + exc.getObjectif() + "' where id='" + exc.getId() + "'";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("exercice updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercice(int id) {
        String req = "delete from exercice where id='" + id + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("exercice deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<exercice> readExercice() {
        ArrayList<exercice> exercices = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM exercice";
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                exercices.add(new exercice(
                        result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                ));
            }

            for (int i = 0; i < exercices.size(); i++) {
                System.out.println("***********************************************************");
                System.out.println(exercices.get(i).getId());
                System.out.println(exercices.get(i).getNom());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exercices;
    }

}
