/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author wassim
 */
public class Event {
    private int id ; 
    private String nom ; 
    private String date_debut ; 
    private String date_fin ;
    private String description ; 
    private int nombre_participants; 
    private String lieu; 
    private String image ; 

    public Event() {
    }

 



    public Event(String nom, String date_debut, String date_fin, String description, int nombre_participants, String lieu, String image) {
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.nombre_participants = nombre_participants;
        this.lieu = lieu;
        this.image = image;
    }

    public Event(int id, String nom, String date_debut, String date_fin, String description, int nombre_participants, String lieu, String image) {
        this.id = id;
        this.nom = nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.nombre_participants = nombre_participants;
        this.lieu = lieu;
        this.image = image;
    }

    public Event(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombre_participants() {
        return nombre_participants;
    }

    public void setNombre_participants(int nombre_participants) {
        this.nombre_participants = nombre_participants;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + ", nombre_participants=" + nombre_participants + ", lieu=" + lieu + ", image=" + image + '}';
    }

    
}
