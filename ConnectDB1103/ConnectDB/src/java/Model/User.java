/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Win_64
 */
public class User implements Serializable{
    private int userID;
    private String email;
    private String fn;
    private String ln;
    
    
    public User() {
        this.userID = 1;
        this.email = "";
        this.fn = "";
        this.ln = "";
    }

    public User(int userID, String email, String fn, String ln) {
        this.userID = userID;
        this.email = email;
        this.fn = fn;
        this.ln = ln;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

   
}
