/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import entities.Event;
import services.ServiceEvent;

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
        Event e = new Event("New Event","02/22/2022","02/23/2022","Nullam iaculis lacus non lectus venenatis tincidunt. Pellentesque est lectus, semper vitae aliquam in, pretium at libero. Nulla sed mauris neque. Nam eget pulvinar turpis, nec iaculis diam. Proin nec cursus libero",10,"moknin","QSVDJEAC41J2G5X4NJ.jpg");
      //se.getallEvent();
      //se.getEventById(17);
      se.createEvent(e);
    }
    
}
