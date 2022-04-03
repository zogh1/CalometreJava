/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Connexion {

    //DB CREDENTIALS
    final static String URL = "jdbc:mysql://127.0.0.1:3306/Calometre";
    final static String USERNAME = "root";
    final static String PWD = "";

    //Connection init
    static Connexion instance = null;
    private Connection cnx;

    //constructor
    public Connexion() {

        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connexion avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //getters
    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }

        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
