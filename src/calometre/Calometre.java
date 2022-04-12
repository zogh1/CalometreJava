/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Aliment;
import java.sql.Connection;
import service.userservice;
import util.connexion;
import service.exerciceService;
import entity.category;
import entity.product;
import entity.RecetteLike;
import entity.user;
import service.categoryservice;
import service.productservice;
import entity.Event;
import entity.Post;
import entity.Recette;
import service.ServiceEvent;
import service.ServicePost;
import entity.Reclamation;
import entity.Reponse;
import entity.exercice;
import entity.typeExercice;
import interfacee.AlimentInterface;
import interfacee.RecetteInterface;
import java.util.ArrayList;
import java.util.List;
import service.AlimentService;
import service.RecetteService;
import service.ServiceReclamation;
import service.ServiceReponse;


/**
 *
 * @author Souhail
 */
public class Calometre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
 float b=233;
  userservice fn = new userservice();
     user test = fn.findById(1);
    

    
      
       RecetteInterface RI=new RecetteService();
       Recette recette=RI.GetById(20);
       
      
     
    System.out.print(RI.getRecetteLikesByRecetteId(recette.getId()));
    
      System.out.print(RI.getNumberOfLikesByRecetteId(recette.getId()));
 
     
   
    
    
   
    
   
    
     
     
 
     
   
   
     
    
    




}
