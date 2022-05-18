/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.logging.Logger;

/**
 *
 * @author louay
 */
public class exercice {

    private int id;
    private typeExercice nomtype_id;
    private String nom;
    private String video;
    private String description;

    public void setCount(int count) {
        this.count = count;
    }
    private String objectif;
    private String color;
    private double rating;
    private int count;
    private double total_rating;

    public int getCount() {
        count++;
        return count;
    }

    public exercice() {
    }

    public void setTotal_rating(double total_rating) {
        this.total_rating = total_rating;
    }

    public double getTotal_rating() {
        return total_rating;
    }

    public exercice(int id, int rating, int count, int total_rating) {
        this.id = id;
        this.rating = rating;
        this.count = count;
        this.total_rating = total_rating;
    }

    public double getRating() {

        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public exercice(int nomtype_id, String nom, String video, String description, String objectif) {

        this.nom = nom;
        this.video = video;
        this.description = description;
        this.objectif = objectif;
    }

    public exercice(int id, int nomtype_id, String nom, String video, String description, String objectif, int count, int rating) {
        this.id = id;

        this.nom = nom;
        this.video = video;
        this.description = description;
        this.objectif = objectif;
        this.count = count;
        this.rating = rating;
        this.total_rating = total_rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public exercice(typeExercice nomtype_id) {
        this.nomtype_id = nomtype_id;
    }
    private static final Logger LOG = Logger.getLogger(exercice.class.getName());

    public typeExercice getNomtype_id() {
        return nomtype_id;
    }

    public void setNomtype_id(typeExercice nomtype_id) {
        this.nomtype_id = nomtype_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "exercice{" + "id=" + id + ", nomtype_id=" + nomtype_id + ", nom=" + nom + ", video=" + video + ", description=" + description + ", objectif=" + objectif + ", color=" + color + ", rating=" + rating + '}';
    }

}
