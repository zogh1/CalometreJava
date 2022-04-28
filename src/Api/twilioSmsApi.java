/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.Arrays;
/**
 *
 * @author wassim
 */
public class twilioSmsApi {



public void sendMessage(String messageBody,String to){
    
    

    try {
        Twilio.init("AC447bbf48744c3c85010adfaff2dad51a", "c6739bcf52bad12db72345cd4cfd49f0");
         Message message = Message.creator(
            new com.twilio.type.PhoneNumber(to),                //Recipient(s)
            new com.twilio.type.PhoneNumber("+12346573908"),    //Sender Phone No. - Find your Twilio phone number at https://www.twilio.com/console
            messageBody)
        .create();
        System.out.println(message.getSid());
    } catch (Exception e) {
        e.printStackTrace();
    }
   
}
}
