/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.models.User;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "registeredUser")
@Dependent
@Entity
@DiscriminatorValue("REGISTERED")
public class RegisteredUser extends User {
    
    protected String telephone;
    protected String password;
    
    @OneToMany(mappedBy = "reportedBy")
    private List<GhostNet> reportedNets;
    
    @OneToMany(mappedBy = "recoveredBy")
    protected List<GhostNet> recoveredNets;

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

    public List<GhostNet> getReportedNets() {
        return reportedNets;
    }

    public void setReportedNets(List<GhostNet> reportedNets) {
        this.reportedNets = reportedNets;
    }

    public List<GhostNet> getRecoveredNets() {
        return recoveredNets;
    }

    public void setRecoveredNets(List<GhostNet> recoveredNets) {
        this.recoveredNets = recoveredNets;
    }
}
