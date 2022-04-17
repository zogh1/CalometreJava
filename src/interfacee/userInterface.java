
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

import entity.user;
import java.util.List;

/**
 *
 * @author admin
 */
public interface userInterface {

    //CRUD
    public void createuser(user u);

    public List<user> getalluser();

    public List<user> pagination();

    public boolean login(user user);

    public user findById(int id);

    public user findByEmail(String email);

    public void updateuser(user u);

    public void deleteuser(int id);

    public boolean verifyEmailExistance(String email);

    public void banUser(int id);

    public void unbanUser(int id);

    public void logout();

    public boolean isConnected();

    public void updatePassword(String oldPassword, String password);

    public boolean passisMatched(String password);

    public List<user> searchUser(String search);

    public List<user> orderUser();

    public int countuserbyRole(String countitem);

}