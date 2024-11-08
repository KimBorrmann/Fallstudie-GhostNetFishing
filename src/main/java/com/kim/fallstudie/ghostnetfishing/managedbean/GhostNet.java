/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.models.User;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "ghostNet")
@Dependent
@Entity
public class GhostNet{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double latitude;
    private double longitude;
    
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User reportedBy;
    @ManyToOne
    private RegisteredUser recoveredBy;
    
    /**
     * Creates a new instance of Netz
     */
    public GhostNet() {
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.size = null;
        this.status = null;
        this.reportedBy = null;
        this.recoveredBy = null;
    }

    public GhostNet(int id, double latitude, double longitude, Size size) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.size = size;
    }
    
    public GhostNet(int id, double latitude, double longitude, Size size, Status status){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.size = size;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RegisteredUser getRecoveredBy() {
        return recoveredBy;
    }

    public void setRecoveredBy(RegisteredUser recoveredBy) {
        this.recoveredBy = recoveredBy;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }
    
    public boolean areFieldsValid() {
        if (latitude == 0 || longitude == 0 || size == null || status == null || reportedBy == null) {
            return false;
        }
        if (status == Status.RECOVERED && recoveredBy == null) {
            return false;
        }
        return true;
    }
}
