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
public class Reclamation {

    private int id;
    private String email;
    private String type;
    private String message;
    private String date;

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", email=" + email + ", type=" + type + ", message=" + message + ", date=" + date + '}';
    }

    public Reclamation(int id, String email, String type, String message, String date) {
        this.id = id;
        this.email = email;
        this.type = type;
        this.message = message;
        this.date = date;
    }

    public Reclamation() {
    }

    public Reclamation(String email, String type, String message, String date) {
        this.email = email;
        this.type = type;
        this.message = message;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
