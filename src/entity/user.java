/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Souhail
 */
public class user {
    private int id;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String roles;
    private boolean isVerfied=false;
    private int phonenumber;
    private int country_code;
    private boolean isbanned=false;
    private String profile_picture;

    public user(int id, String password, String firstname, String lastname, String email, String roles, int phonenumber, int country_code, String profile_picture) {
        this.id = id;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roles = roles;
        this.phonenumber = phonenumber;
        this.country_code = country_code;
        this.profile_picture = profile_picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isIsVerfied() {
        return isVerfied;
    }

    public void setIsVerfied(boolean isVerfied) {
        this.isVerfied = isVerfied;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getCountry_code() {
        return country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }

    public boolean isIsbanned() {
        return isbanned;
    }

    public void setIsbanned(boolean isbanned) {
        this.isbanned = isbanned;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
     @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
