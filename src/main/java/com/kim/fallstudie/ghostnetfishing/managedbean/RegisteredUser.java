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
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "registeredUser")
@Dependent
@Entity
@DiscriminatorValue("REGISTERED")
public class RegisteredUser extends User implements Serializable {
    
    protected String telephone;
    protected String password;
    
    @OneToMany(mappedBy = "reportedBy", fetch = FetchType.EAGER)
    private List<GhostNet> reportedNets;
    
    @OneToMany(mappedBy = "recoveredBy", fetch = FetchType.EAGER)
    protected List<GhostNet> recoveredNets;

    /**
     * Creates a new instance of Nutzer
     */
    public RegisteredUser() {
    }

    public RegisteredUser(String password, String username) {
        super(username);
        this.password = password;
    }

    public RegisteredUser(String telephone, String password, String username) {
        super(username);
        this.telephone = telephone;
        this.password = password;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.telephone);
        hash = 83 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegisteredUser other = (RegisteredUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }
    
    
}
