/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author Ahmed Mahjoub
 */
public class Recette {
    private int id;
    private String nom;
    private String regime;
    private Float totcalories;
    private String categorie;
    private String image;
    private  List<Aliment> listAliment;
    private  List<RecetteLike> Likes;
    private int totlikes;
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public void setTotcalories(Float totcalories) {
        this.totcalories = totcalories;
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

    public String getRegime() {
        return regime;
    }

    public Float getTotcalories() {
        return totcalories;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getImage() {
        return image;
    }

    public void setTotlikes(int totlikes) {
        this.totlikes = totlikes;
    }

    public int getTotlikes() {
        return totlikes;
    }

    public Recette() {
    }

    public Recette(String nom, String regime, String categorie, String image) {
        this.nom = nom;
        this.regime = regime;
        this.categorie = categorie;
        this.image = image;
    }

    public Recette(int id, String nom, String regime, String categorie, String image) {
        this.id = id;
        this.nom = nom;
        this.regime = regime;
        this.categorie = categorie;
        this.image = image;
    }

    public Recette(String nom, String regime, Float totcalories, String categorie, String image) {
        this.nom = nom;
        this.regime = regime;
        this.totcalories = totcalories;
        this.categorie = categorie;
        this.image = image;
    }

    public Recette(int id, String nom, String regime, Float totcalories, String categorie, String image) {
        this.id = id;
        this.nom = nom;
        this.regime = regime;
        this.totcalories = totcalories;
        this.categorie = categorie;
        this.image = image;
    }

    public Recette(int id, String nom, String regime, String categorie, String image, int totlikes) {
        this.id = id;
        this.nom = nom;
        this.regime = regime;
        this.categorie = categorie;
        this.image = image;
        this.totlikes = totlikes;
    }
    

            
     
    

    @Override
    public String toString() {
        return "Recette{" + "id=" + id + ", nom=" + nom + ", regime=" + regime + ", totcalories=" + totcalories + ", categorie=" + categorie + ", image=" + image + '}';
    }

    public void setListAliment(List<Aliment> listAliment) {
        this.listAliment = listAliment;
    }

    public void setLikes(List<RecetteLike> Likes) {
        this.Likes = Likes;
    }

    public List<Aliment> getListAliment() {
        return listAliment;
    }

    public List<RecetteLike> getLikes() {
        return Likes;
    }
    
    
  
  
}
