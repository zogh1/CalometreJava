/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.typeExercice;
import interfacee.typeExerciceInterface;
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
public class typeExerciceService implements typeExerciceInterface {

    Connection cnx = connexion.getInstance().getCnx();
    Statement stmt;

    @Override
    public void addType(typeExercice type) {
        try {
            String req = "INSERT INTO `typeexercice`(`nom`) VALUES (?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, type.getNom());
            st.executeUpdate();

            System.out.println("type added successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editType(typeExercice type) {
        try {
            String req = "update typeexercice set nom='" + type.getNom() + "' where id='" + type.getId() + "'";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("nom updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteType(int id) {
        String req = "delete from typeexercice where id='" + id + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("type deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<typeExercice> readType() {
        ArrayList<typeExercice> types = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String req = "SELECT * FROM typeexercice";
            ResultSet result = st.executeQuery(req);
            while (result.next()) {
                types.add(new typeExercice(
                        result.getInt(1),
                        result.getString(2)
                ));
            }

            for (int i = 0; i < types.size(); i++) {
                System.out.println("***********************************************************");
                System.out.println(types.get(i).getId());
                System.out.println(types.get(i).getNom());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return types;
    }

}
