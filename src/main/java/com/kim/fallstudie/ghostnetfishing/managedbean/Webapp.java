/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.GhostNetDAO;
import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.UserDAO;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "webapp")
@ApplicationScoped
public class Webapp {
    
    protected List<GhostNet> reportedNets = new ArrayList<>();
    protected List<GhostNet> recoveredNets = new ArrayList<>();
    protected List<RegisteredUser> registeredUsers = new ArrayList<>();
    protected RegisteredUser currentUser;
    private final GhostNetDAO ghostNetDAO;
    private final UserDAO userDAO;
    /**
     * Creates a new instance of Webapp
     */
    public Webapp() {
        ghostNetDAO = new GhostNetDAO();
        userDAO = new UserDAO();
        
        registeredUsers = getAllUsers();
        reportedNets = getReportedNets();
        recoveredNets = getRecoveredNets();
        
        currentUser = registeredUsers.getFirst();
    } 

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(List<RegisteredUser> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }

    public List<GhostNet> getReportedNets() {
        return ghostNetDAO.findAllNotRecovered();
    }

    public void setReportedNets(List<GhostNet> reportedNets) {
        this.reportedNets = reportedNets;
    }

    public List<GhostNet> getRecoveredNets() {
        return ghostNetDAO.findRecovered();
    }

    public void setRecoveredNets(List<GhostNet> recoveredNets) {
        this.recoveredNets = recoveredNets;
    }
    
    public void saveNet(GhostNet newNet){
        //Write new net to db if the fields are valid
        if(newNet.areFieldsValid()){
            ghostNetDAO.saveNet(newNet);
        }
    }
    
    public List<GhostNet> getAllNets() {
        return ghostNetDAO.findAll();
    }
    
    public List<RegisteredUser> getAllUsers(){
        return userDAO.findAllRegistered();
    }
}
