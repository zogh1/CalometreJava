/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.user;
import service.userservice;


/**
 *
 * @author Souhail
 */
public class session {

    private static final userservice fs = new userservice();

    private static session instance = null;
    private static user user = null;

    private session(user userConnected) {
        super();
        session.user = userConnected;

    }

    private session(user userConnected, Boolean b) {
        super();
        session.user = userConnected;
    }

    public final static session getInstance(user userConnected) {

        if (session.instance == null) {
            session.instance = new session(userConnected);
        }
        return session.instance;
    }

    public final static session getFirstInstance(user userConnected) {

        if (session.instance == null) {

            session.instance = new session(userConnected, false);

        }
        return session.instance;
    }

    public static userservice getFs() {
        return fs;
    }

    public static session getInstance() {
        return instance;
    }

    public static user getUser() {
        return user;
    }

    public static void setUser(user user) {
        session.user = user;
    }

}
