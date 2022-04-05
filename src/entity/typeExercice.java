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
public class typeExercice {

    private int id;
    private String nom;

    public typeExercice() {
    }

    public typeExercice(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public typeExercice(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "typeExercice{" + "id=" + id + ", nom=" + nom + '}';
    }

}
