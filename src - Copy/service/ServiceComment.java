/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Comment;
import entity.Event;
import entity.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.connexion;

/**
 *
 * @author wassim
 */
public class ServiceComment {
    //ajout d'un commentaire
    
    public void addComment(Comment c) {
        String req = "insert into comment(event_id,commentdate,commentcontent,user_id,likecount) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);

            ps.setInt(1, c.getEvent_id());
            ps.setString(2, c.getCommentdate());
            ps.setString(3, c.getCommentcontent());
            ps.setInt(4, c.getUser_id());
            ps.setInt(5, c.getLikecount());

            ps.executeUpdate();
            System.out.println("Comment added !!!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //supprimer le commentaire par Id
    public void removeCommentById(int id) {

        String req = "delete from comment where id=?";
        try {
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    public List<Comment> getCommentsByEvent(int comment_id) {
        List<Comment> le = new ArrayList<Comment>();
        String req = "SELECT id,commentcontent,likecount,user_id FROM `comment` WHERE event_id=" + comment_id + " order by commentdate desc;";
        try {
            Statement s = connexion.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setLikecount(rs.getInt("likecount"));
                c.setCommentcontent(rs.getString("commentcontent"));
                System.out.println(c);

                le.add(c);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return le;
    }
 public void addLike(int id) {

        String req = "update comment set likecount = likecount + 1 where id=?";
        try {
            System.out.println("zid j'aime lel comment " + id);
            PreparedStatement ps = connexion.getInstance().getCnx().prepareStatement(req);
            
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
            System.out.println("erreur dans la methode addLike ");
        }
    }
}
