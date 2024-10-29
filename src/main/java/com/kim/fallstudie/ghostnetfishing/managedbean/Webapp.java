/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.enums.Status;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "webapp")
@ApplicationScoped
public class Webapp {
    
    protected List<GhostNet> reportedNets = new ArrayList<>();
    protected List<RegisteredUser> registeredUsers = new ArrayList<>();

    /**
     * Creates a new instance of Webapp
     */
    public Webapp() {
        registeredUsers.add(new RegisteredUser("012345678", "myPassword12", 1, "Anna Fischretter"));
        
        reportedNets.add(new GhostNet(1, new LatLng(-61.45480, -41.67364), Size.MEDIUM, Status.REPORTED ));
        reportedNets.add(new GhostNet(2, new LatLng(23.76162, -42.22665), Size.LARGE, Status.LOST ));
        reportedNets.add(new GhostNet(2, new LatLng(-47.95458, -122.56537), Size.SMALL, Status.ALLOCATED));
        reportedNets.get(2).setReserved(registeredUsers.getFirst());
    }

    public List<GhostNet> getReportedNets() {
        return reportedNets;
    }

    public void setReportedNets(List<GhostNet> reportedNets) {
        this.reportedNets = reportedNets;
    }
}
