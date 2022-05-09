/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.exercice;
import entity.product;
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
    typeExerciceService tex = new typeExerciceService();

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
    public void editExercice(exercice exc, typeExercice type) {

        String req = "UPDATE `exercice` SET `nom`='" + exc.getNom() + "',`video`='" + exc.getVideo() + "',`description`='" + exc.getDescription() + "',`objectif`='" + exc.getObjectif() + "' ,`nomtype_id`='" + type.getId() + "' WHERE id='" + exc.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate(req);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }
//    @Override
//    public void editExercice(exercice exc, typeExercice type) {
//        try {
//            String req = "update exercice set nom='" + exc.getNom() + "',video='" + exc.getVideo() + "',description='" + exc.getDescription() + "',objectif='" + exc.getObjectif() + "' ,`type_exercice`='" + type.getId() + "' where id='" + exc.getId() + "'";
//
//            PreparedStatement ps = cnx.prepareStatement(req);
//            ps.executeUpdate(req);
//            System.out.println("exercice updated");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
    public List<exercice> searchExercice(String search) {
        List<exercice> li = new ArrayList();

        try {
            String req = "SELECT * FROM exercice WHERE nom LIKE '%" + search + "%'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                exercice ex = new exercice();
                ex.setId(rs.getInt("id"));
                ex.setNom(rs.getString("nom"));
                 ex.setVideo(rs.getString("video"));
                 ex.setObjectif(rs.getString("objectif"));
                 ex.setDescription(rs.getString("description"));
                 
                 ex.setNomtype_id(tex.findById(rs.getInt("nomtype_id")));
                 
                li.add(ex);
                
            }
            System.out.println(li);
        } catch (SQLException ex) {
              ex.printStackTrace();
        }

        return li;

    }

   
    public List<exercice> readExercice() {
        List<exercice> li = new ArrayList<exercice>();

        try {
            String req = "select * from exercice";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                exercice ex = new exercice();
                ex.setId(rs.getInt("id"));
                ex.setNom(rs.getString("nom"));
                ex.setVideo(rs.getString("video"));
                ex.setDescription(rs.getString("description"));
                ex.setObjectif(rs.getString("objectif"));
                ex.setNomtype_id(tex.findById(rs.getInt("nomtype_id")));
                
                li.add(ex);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return li;
    }

}
