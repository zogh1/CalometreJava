/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author wassim
 */
public class Post {
    private int id ; 
  
      private String name ; 
      private String  creation_date ; 
       private String description ; 

    public Post() {
    }

    public Post(String name, String creation_date, String description, Event ev) {
        this.name = name;
        this.creation_date = creation_date;
        this.description = description;
        this.ev = ev;
    }

    
   

    public Post(int id, String name, String creation_date, String description) {
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   Event ev ; 

    public Event getEv() {
        return ev;
    }

    public void setEv(Event ev) {
        this.ev = ev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id +  ", name=" + name + ", creation_date=" + creation_date + ", description=" + description + '}';

//To change body of generated methods, choose Tools | Templates.
    }



  
    

   
 
      
    
}
