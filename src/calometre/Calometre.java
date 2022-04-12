/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entity.Event;
import entity.Post;
import service.ServiceEvent;
import service.ServicePost;

/**
 *
 * @author wassim
 */
public class Calometre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    ServiceEvent se = new ServiceEvent();
        se.applyToEvent(6, 17);
    }
    
}
