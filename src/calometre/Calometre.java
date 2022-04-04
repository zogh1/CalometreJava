/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calometre;

import service.exerciceService;

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
        /*userservice fn = new userservice();
        user test = new user();

        test.setEmail("testmail");
        test.setPassword("password");

        fn.login(test);*/

        exerciceService es = new exerciceService();

        es.deleteExercice(8);

//        test.setCountry_code("216");
//        test.setEmail("test");
//        test.setFirstname("hghghgh");
//        test.setLastname("rtrtt");
//        test.setPassword("ttttt");
//        test.setPhonenumber(1213);
//        test.setProfile_picture("fghfhgf");
//        test.setRoles("tttt");
//       fn.deleteuser(32);
        /*user edit =new user();

        edit.setCountry_code("216");
        edit.setEmail("test");
        edit.setFirstname("hghghgh");
        edit.setLastname("rtrddtt");
        edit.setPassword("ttttt");
        edit.setPhonenumber(1213);
        edit.setProfile_picture("fghfhgf");
        edit.setRoles("tttt");
        edit.setId(28);
        fn.updateuser(edit);*/
    }

}
