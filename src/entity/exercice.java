/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author louay
 */
public class exercice {

    private int id;
    private int nomtype_id;
    private String nom;
    private String video;
    private String description;
    private String objectif;

    public exercice() {
    }

    public exercice(int nomtype_id, String nom, String video, String description, String objectif) {
        this.nomtype_id = nomtype_id;
        this.nom = nom;
        this.video = video;
        this.description = description;
        this.objectif = objectif;
    }

    public exercice(int id, int nomtype_id, String nom, String video, String description, String objectif) {
        this.id = id;
        this.nomtype_id = nomtype_id;
        this.nom = nom;
        this.video = video;
        this.description = description;
        this.objectif = objectif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNomtype_id() {
        return nomtype_id;
    }

    public void setNomtype_id(int nomtype_id) {
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

    @Override
    public String toString() {
        return "exercice{" + "id=" + id + ", nomtype_id=" + nomtype_id + ", nom=" + nom + ", video=" + video + ", description=" + description + ", objectif=" + objectif + '}';
    }

}
