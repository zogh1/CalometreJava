/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author RYM BACCOURI
 */
public class Reponse {
     private int id;
    private String date;
    private int repondre_id;
    private String reponse;

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", date=" + date + ", repondre_id=" + repondre_id + ", reponse=" + reponse + '}';
    }

    public Reponse(int id, String date, int repondre_id, String reponse) {
        this.id = id;
        this.date = date;
        this.repondre_id = repondre_id;
        this.reponse = reponse;
    }

    public Reponse() {
    }

    public Reponse(String date, int repondre_id, String reponse) {
        this.date = date;
        this.repondre_id = repondre_id;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRepondre_id() {
        return repondre_id;
    }

    public void setRepondre_id(int repondre_id) {
        this.repondre_id = repondre_id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
   
    
}
