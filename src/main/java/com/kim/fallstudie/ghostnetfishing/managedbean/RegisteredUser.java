/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.models.User;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "nutzer")
@Dependent
public class RegisteredUser extends User {
    
    protected String telephone;
    protected String password;
    protected List<GhostNet> allocatedNets;

    /**
     * Creates a new instance of Nutzer
     */
    public RegisteredUser() {
    }

    public RegisteredUser(String telephone, String password, int userId, String username) {
        super(userId, username);
        this.telephone = telephone;
        this.password = password;
    }

    public RegisteredUser(String telephone, String password, List<GhostNet> allocatedNets, int userId, String username) {
        super(userId, username);
        this.telephone = telephone;
        this.password = password;
        this.allocatedNets = allocatedNets;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void allocateNet(int netId){
        //TODO:implent
    }
    
    public void reportRecovery(int netId){
        //TODO:implent
    }
}
