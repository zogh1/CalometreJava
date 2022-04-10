
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import java.util.List;
import entity.user;

/**
 *
 * @author admin
 */
public interface userInterface {

    //CRUD
    public void createuser(user u);

    public List<user> getalluser();

    public boolean login(user user);

    public user findById(int id);

    public void updateuser(user u);

    public void deleteuser(int id);

}
