/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.Reclamation;
/**
 *
 * @author RYM BACCOURI
 */
public class sendSMS {
    
    public static final String ACCOUNT_SID = System.getenv("ACe311204cd84cebcf4e918dda1e4fba28");
    public static final String AUTH_TOKEN = System.getenv("848aecd860b5ceca785ea2992a2f414d");

    public static void sendSMS(Reclamation r) {
        Twilio.init("ACe311204cd84cebcf4e918dda1e4fba28", "848aecd860b5ceca785ea2992a2f414d");
        Message message = Message.creator(new PhoneNumber("+21654076565"),
        new PhoneNumber("+16205578743"), 
        "ID: "+r.getId()+" Email: "+r.getEmail()+" Type: "+r.getType()+"Message: "+r.getMessage()).create();
       

        System.out.println(message.getSid());
    }
    
}
