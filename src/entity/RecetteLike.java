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
public class RecetteLike {
    int id ;
    user user;
    Recette recette;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public RecetteLike(user user, Recette recette) {
        this.user = user;
        this.recette = recette;
    }

    public RecetteLike() {
    }

    public RecetteLike(int id, user user, Recette recette) {
        this.id = id;
        this.user = user;
        this.recette = recette;
    }
    
    
    
    public void AddLike(RecetteLike Rl)
    {
    
    }
    
    
}
