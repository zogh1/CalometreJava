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
public class RecRep {
    private int id;
    private String email;
    private String date;
    private String type;
    private String message;
    private String reponse;

    public RecRep(int id, String email, String date, String type, String message, String reponse) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.type = type;
        this.message = message;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "RecRep{" + "id=" + id + ", email=" + email + ", date=" + date + ", type=" + type + ", message=" + message + ", reponse=" + reponse + '}';
    }
    
    
    
}
