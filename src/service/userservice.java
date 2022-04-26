/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.user;

import interfacee.userInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import util.BCrypt;
import static util.EmailSender.sendEmailWithAttachments;

import java.util.List;
import entity.user;
import util.BCrypt;

import util.connexion;
import util.session;

/**
 *
 * @author Souhail
 */
public class userservice implements userInterface {


    public static int code;
    public static String email;


    //var
    connexion instance = connexion.getInstance();
    Connection cnx = instance.getCnx();

    @Override
    public void createuser(user u) {

        //request
        try {
            String req = "INSERT INTO `user`(`password`, `email`, `roles`, `is_verified`, `firstname`, `lastname`, `phonenumber`, `profile_picture`, `isbanned`, `country_code`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            st.setString(2, u.getEmail());

            st.setString(3, "[\"" + u.getRoles() + "\"]");

            st.setBoolean(4, false);
            st.setString(5, u.getFirstname());
            st.setString(6, u.getLastname());
            st.setInt(7, u.getPhonenumber());
            st.setString(8, u.getProfile_picture());
            st.setBoolean(9, false);
            st.setString(10, u.getCountry_code());
            st.executeUpdate();
            System.out.println("Personne ajoutée avec succes.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<user> getalluser() {
        List<user> li = new ArrayList<user>();

        try {
            String req = "select * from user";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                user l = new user();
                l.setId(rs.getInt("id"));
                l.setEmail(rs.getString("email"));
                l.setRoles(rs.getString("roles"));
                l.setIsVerfied(rs.getBoolean("is_verified"));
                l.setFirstname(rs.getString("firstname"));
                l.setLastname(rs.getString("lastname"));
                l.setPhonenumber(rs.getInt("phonenumber"));
                l.setProfile_picture(rs.getString("profile_picture"));
                l.setIsbanned(rs.getBoolean("isbanned"));
                l.setCountry_code(rs.getString("country_code"));

                li.add(l);
            }
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getEmail());
                System.out.println(li.get(i).getFirstname());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return li;
    }

    @Override

    public List<user> pagination() {
        List<user> li = new ArrayList<user>();

        try {
            String req = "select * from user LIMIT 3 OFFSET 1 ";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                user l = new user();
                l.setId(rs.getInt("id"));
                l.setEmail(rs.getString("email"));
                l.setRoles(rs.getString("roles"));
                l.setIsVerfied(rs.getBoolean("is_verified"));
                l.setFirstname(rs.getString("firstname"));
                l.setLastname(rs.getString("lastname"));
                l.setPhonenumber(rs.getInt("phonenumber"));
                l.setProfile_picture(rs.getString("profile_picture"));
                l.setIsbanned(rs.getBoolean("isbanned"));
                l.setCountry_code(rs.getString("country_code"));

                li.add(l);
            }
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getEmail());
                System.out.println(li.get(i).getFirstname());
            }

        } catch (SQLException ex) {
        }

        return li;

    }

    @Override

    public void deleteuser(int id) {

        try {
            String req = "delete from user where id=?";
            PreparedStatement st = connexion.getInstance().getCnx().prepareStatement(req);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("user delete error");
            ex.printStackTrace();
        }
    }

    @Override
    public void updateuser(user u) {

        String req = "UPDATE `user` SET `password`='" + u.getPassword() + "',`email`='" + u.getEmail() + "',`roles`='" + u.getRoles() + "',`is_verified`=0,`firstname`='" + u.getFirstname() + "',`lastname`='" + u.getLastname() + "',`phonenumber`='" + u.getPhonenumber() + "',`profile_picture`='" + u.getProfile_picture() + "',`isbanned`=0,`country_code`='" + u.getCountry_code() + "' WHERE id='" + u.getId() + "'";
        try {
            PreparedStatement st = cnx.prepareStatement(req);

            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }

    @Override
    public user findById(int id) {
        user u = null;
        try {
            String req = "select * from user where id=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new user(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),

                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    @Override
    public user findByEmail(String email) {
        user u = null;
        try {
            String req = "select * from user where email=? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                u = new user(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),

                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11));
            }

            System.out.println(u.getFirstname());

        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }

    @Override

    public String getRole() {
        String role = null;
        System.out.println(session.getUser().getRoles());
        if (session.getUser().getRoles().contains("ADMIN")) {
            role = "admin";
            System.out.println("admin");
            return role;
        }

        if (session.getUser().getRoles().contains("CLIENT")) {
            role = "coach";
            System.out.println("client");
            return role;
        }

        if (session.getUser().getRoles().contains("COACH")) {
            role = "coach";
            System.out.println("coach");
            return role;
        }

        return role;
    }

    @Override
    public int countBannedAccounts() {
        int i = 0;
        try {
            String req = "SELECT COUNT(*) FROM `user` WHERE isbanned=1 ";
            Statement ps = cnx.createStatement();
            ResultSet result = ps.executeQuery(req);
            result.next();
            i = result.getInt(1);
        } catch (SQLException e) {
        }
        return i;
    }

    @Override
    public HashMap<String, Integer> getStatTypeOfUsers() {
        List<user> list = this.getalluser();
        HashMap<String, Integer> stat = new HashMap<>();
        stat.put("Admins", 0);
        stat.put("Clients", 0);
        stat.put("Coaches", 0);

        list.forEach((user) -> {
            if (user.getRoles().contains("ADMIN")) {
                stat.put("Admins", stat.get("Admins") + 1);
            } else if (user.getRoles().contains("Coach")) {
                stat.put("Coaches", stat.get("Coaches") + 1);
            } else if (user.getRoles().contains("CLIENT")) {
                stat.put("Clients", stat.get("Clients") + 1);
            }
        });
        return stat;
    }

    @Override

    public boolean login(user user) {
        boolean status = false;
        try {
            String req = "SELECT * FROM `user` WHERE email=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (BCrypt.checkpw(user.getPassword(), rs.getString("password"))) {
                    status = true;
                    user = this.findById(rs.getInt("id"));
                    session.setUser(user);
                    System.out.println("connected");


                } else {
                    status = false;
                    System.out.println("invalid credentials");
                }
            }


        } catch (SQLException e) {
            System.err.println("wrong");
        }
        return status;
    }

    @Override
    public boolean verifyEmailExistance(String email) {
        boolean isFound = false;

        try {

            String req = "select *  from user where email='" + email + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                isFound = true;

            }

        } catch (SQLException e) {
        }

        return isFound;
    }

    @Override
    public void banUser(int id) {

        try {
            String req = "update user set isbanned=1 where id='" + id + "'";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.executeUpdate(req);
            System.out.println("user banned");

        } catch (SQLException e) {
        }

    }

    @Override
    public void unbanUser(int id) {

        try {
            String req = "update user set isbanned=0 where id='" + id + "'";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.executeUpdate(req);
            System.out.println("user unbanned");

        } catch (SQLException e) {
        }

    }

    @Override
    public void logout() {

        session.setUser(null);
        System.out.println("logout successful");

    }

    @Override
    public boolean isConnected() {
        boolean connected = false;
        if (session.getUser() != null) {
            connected = true;

        }
        return connected;
    }

    @Override
    public void updatePassword(String oldPassword, String password) {

        if (passisMatched(oldPassword)) {

            try {

                String passwordEnc = BCrypt.hashpw(password, BCrypt.gensalt());
                String req = "update user set password='" + passwordEnc + "' where id='" + session.getUser().getId() + "'";

                PreparedStatement ps = cnx.prepareStatement(req);
                ps.executeUpdate();
                System.out.println("password updated");
            } catch (Exception ex) {
                System.out.println("error");
            }
        } else {
            System.err.println("passdontmatch");
        }
    }

    @Override
    public List<user> searchUser(String search) {
        List<user> li = new ArrayList();

        try {
            String req = "SELECT * FROM user WHERE email LIKE '%" + search + "%'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                user l = new user();
                l.setId(rs.getInt("id"));
                l.setEmail(rs.getString("email"));
                l.setRoles(rs.getString("roles"));
                l.setIsVerfied(rs.getBoolean("is_verified"));
                l.setFirstname(rs.getString("firstname"));
                l.setLastname(rs.getString("lastname"));
                l.setPhonenumber(rs.getInt("phonenumber"));
                l.setProfile_picture(rs.getString("profile_picture"));
                l.setIsbanned(rs.getBoolean("isbanned"));
                l.setCountry_code(rs.getString("country_code"));

                li.add(l);
            }
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getEmail());
                System.out.println(li.get(i).getFirstname());
            }

        } catch (SQLException ex) {
        }

        return li;

    }

    @Override
    public List<user> orderUser() {
        List<user> li = new ArrayList();

        try {
            String req = "select * from user order by firstname desc";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                user l = new user();
                l.setId(rs.getInt("id"));
                l.setEmail(rs.getString("email"));
                l.setRoles(rs.getString("roles"));
                l.setIsVerfied(rs.getBoolean("is_verified"));
                l.setFirstname(rs.getString("firstname"));
                l.setLastname(rs.getString("lastname"));
                l.setPhonenumber(rs.getInt("phonenumber"));
                l.setProfile_picture(rs.getString("profile_picture"));
                l.setIsbanned(rs.getBoolean("isbanned"));
                l.setCountry_code(rs.getString("country_code"));

                li.add(l);
            }
            for (int i = 0; i < li.size(); i++) {
                System.out.println("*********");
                System.out.println(li.get(i).getEmail());
                System.out.println(li.get(i).getFirstname());
            }

        } catch (SQLException ex) {
        }

        return li;

    }

    public int countuserbyRole(String countitem) {
        int nb = 0;
        try {
            String req = "select count(*) from user where roles LIKE '%" + countitem + "%'";
            Statement s = cnx.createStatement();

            ResultSet rs = s.executeQuery(req);
            rs.next();
            nb = rs.getInt(1);

            System.out.println("*********");
            System.out.println(nb);

        } catch (SQLException ex) {
        }

        return nb;

    }

    @Override
    public boolean passisMatched(String password) {
        boolean passMatched = false;
        if (BCrypt.checkpw(password, session.getUser().getPassword())) {
            passMatched = true;

        }
        return passMatched;

    }

    @Override
    public boolean sendresetCode(String email) {
        boolean isSent = false;

        String subject = "test";
        String message = "your reset code is " + this.randomNumber();
        try {
            sendEmailWithAttachments(email,
                    subject, message);
            System.out.println("Email sent.");
            isSent = true;
            System.out.println(isSent);
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
        return isSent;

    }

    @Override
    public boolean resetcodeisMatched(int userinput) {
        boolean resetcodeMatched = false;

        if (userservice.code == userinput) {
            System.out.println("true");
            resetcodeMatched = true;
        } else {
            System.err.println("wrong");
        }

        return resetcodeMatched;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        boolean reset = false;
        try {
            reset = true;
            String passwordEnc = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String req = "update user set password='" + passwordEnc + "' where email='" + email + "'";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("password updated");

        } catch (SQLException e) {
        }
        return reset;
    }

    @Override
    public int randomNumber() {

        Random rand = new Random();

        userservice.code = rand.nextInt(999999);

        userservice.code += 1;

        System.out.println("random is " + userservice.code);
        return userservice.code;

    }
}
