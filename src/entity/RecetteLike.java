/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import interfacee.RecetteInterface;
import interfacee.userInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.RecetteService;
import service.userservice;
import util.connexion;

/**
 *
 * @author Ahmed Mahjoub
 */
public class RecetteLike {
    int id ;
    user user;
    Recette recette;
      connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();
    RecetteInterface RI=new RecetteService();
    userInterface UI =new userservice();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public RecetteLike(user user, Recette recette) {
        this.user = user;
        this.recette = recette;
    }

    public RecetteLike() {
    }

    public RecetteLike(int id, user user, Recette recette) {
        this.id = id;
        this.user = user;
        this.recette = recette;
    }
    
    public int getUserId (){return this.getUser().getId();}
    
    
public int getRecetteId (){return this.getRecette().getId();}
public RecetteLike getRecetteLikeById ( int id){
     ArrayList<Recette> likes = new ArrayList();
              RecetteLike a =new RecetteLike();
              
          String req = "SELECT * FROM `recette_like` WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
              while (rs.next()) {      
            
             a = new RecetteLike(rs.getInt("id"),UI.findById(rs.getInt("user_id")),RI.GetById(rs.getInt("recette_id")));
         
              }
            }
            
         catch (SQLException ex) {
          ex.printStackTrace();
        }
        return a ;

}

    @Override
    public String toString() {
        return "RecetteLike{" + "user=" + user + ", recette=" + recette + '}';
    }
    

    
 
    }
    
    

