/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users_login;

/**
 *
 * @author CHRIS
 */
public class HeadMaster {
    
    private String username;
    private String pass;
    
    public HeadMaster(){
    
    }

    public HeadMaster(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "HeadMaster{" + "username=" + username + ", pass=" + pass + '}';
    }
    
}
