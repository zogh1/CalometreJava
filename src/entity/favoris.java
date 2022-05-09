/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author yassine
 */
public class favoris {
    private int id;
    private int id_exercice;
    private int id_user;

    public favoris(int id, int id_exercice, int id_user) {
        this.id = id;
        this.id_exercice = id_exercice;
        this.id_user = id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_exercice(int id_exercice) {
        this.id_exercice = id_exercice;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public int getId_exercice() {
        return id_exercice;
    }

    public int getId_user() {
        return id_user;
    }
    
}
