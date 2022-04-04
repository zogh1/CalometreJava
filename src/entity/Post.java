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
      private int event_id ; 
      private String name ; 
      private Date creation_date ; 
       private String description ; 

    public Post() {
    }

    public Post(int id, int event_id, String name, Date creation_date, String description) {
        this.id = id;
        this.event_id = event_id;
        this.name = name;
        this.creation_date = creation_date;
        this.description = description;
    }

    public Post(int id, String name, Date creation_date, String description) {
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

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
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
        return "Post{" + "id=" + id + ", event_id=" + event_id + ", name=" + name + ", creation_date=" + creation_date + ", description=" + description + '}';

//To change body of generated methods, choose Tools | Templates.
    }



  
    

   
 
      
    
}
