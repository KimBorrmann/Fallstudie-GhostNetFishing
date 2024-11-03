/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

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
    /**
     * Creates a new instance of Webapp
     */
    public Webapp() {
        registeredUsers.add(new RegisteredUser("012345678", "myPassword12", 1, "Anna Fischretter"));
        
        reportedNets.add(new GhostNet(1, -61.45480, -41.67364, Size.MEDIUM, Status.REPORTED ));
        reportedNets.add(new GhostNet(2, 23.76162, -42.22665, Size.LARGE, Status.LOST ));
        reportedNets.add(new GhostNet(3, -47.95458, -122.56537, Size.SMALL, Status.ALLOCATED));
        reportedNets.get(2).setReserved(registeredUsers.getFirst());
        
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
        //Write new net to db
        reportedNets.add(newNet);
    }
}
