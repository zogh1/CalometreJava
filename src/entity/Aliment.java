/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Ahmed Mahjoub
 */
public class Aliment {
    private int id;
    private String nom;
    private Float calories;
    private String categorie;
    private String image;

    public Aliment(int id, String nom, Float calories, String categorie, String image) {
        this.id = id;
        this.nom = nom;
        this.calories = calories;
        this.categorie = categorie;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Aliment{" + "id=" + id + ", nom=" + nom + ", calories=" + calories + ", categorie=" + categorie + ", image=" + image + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Float getCalories() {
        return calories;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getImage() {
        return image;
    }

    public Aliment(String nom, Float calories, String categorie, String image) {
        this.nom = nom;
        this.calories = calories;
        this.categorie = categorie;
        this.image = image;
    }

    public Aliment() {
    }
    
}
