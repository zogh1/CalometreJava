
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacee;

<<<<<<< HEAD
import entity.user;
import java.util.HashMap;
import java.util.List;
=======
import java.util.List;
import entity.user;
>>>>>>> reclamationjava

/**
 *
 * @author admin
 */
public interface userInterface {

    //CRUD
    public void createuser(user u);

    public List<user> getalluser();

<<<<<<< HEAD
    public List<user> pagination();

=======
>>>>>>> reclamationjava
    public boolean login(user user);

    public user findById(int id);

<<<<<<< HEAD
    public user findByEmail(String email);

=======
>>>>>>> reclamationjava
    public void updateuser(user u);

    public void deleteuser(int id);

<<<<<<< HEAD
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

    public boolean sendresetCode(String email);

    public boolean resetcodeisMatched(int userinput);

    public int randomNumber();

    public boolean resetPassword(String email, String newPassword);

    public HashMap<String, Integer> getStatTypeOfUsers();

    public int countBannedAccounts();

    public String getRole();

=======
>>>>>>> reclamationjava
}
