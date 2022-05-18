/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.Event;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface IServiceEvent {
    //crud 
    public void createEvent(Event e);
    public List<Event> readEvents();
}
