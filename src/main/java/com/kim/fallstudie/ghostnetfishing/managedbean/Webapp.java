/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.GhostNetDAO;
import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.UserDAO;
import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.models.User;
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
    protected List<RegisteredUser> registeredUsers = new ArrayList<>();
    protected RegisteredUser currentUser;
    private GhostNetDAO ghostNetDAO;
    private UserDAO userDAO;
    /**
     * Creates a new instance of Webapp
     */
    public Webapp() {
        ghostNetDAO = new GhostNetDAO();
        //userDAO = new UserDAO();
        
        //registeredUsers = getAllUsers();
        reportedNets = getAllNets();
        registeredUsers.add(new RegisteredUser("012345678", "myPassword12", 1, "Anna Fischretter"));
        
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
        return reportedNets;
    }

    public void setReportedNets(List<GhostNet> reportedNets) {
        this.reportedNets = reportedNets;
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
    
    /*public List<RegisteredUser> getAllUsers(){
        return userDAO.findAll();
    }*/
    
    /*public void editNet(int id, GhostNet editedNet){
        boolean containsId = reportedNets.stream().anyMatch(net -> net.getId() == id);
        int index = -1;
        if(containsId){
            for(int i = 0; i< reportedNets.size(); i++){
                if(reportedNets.get(i).getId() == id){
                    index = i;
                    break;
                }
            }
            
            if(index != -1){
                reportedNets.set(index, editedNet);
            }
        }
    }*/
}
