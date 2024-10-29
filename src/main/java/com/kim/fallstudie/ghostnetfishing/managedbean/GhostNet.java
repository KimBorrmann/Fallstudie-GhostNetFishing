/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.enums.Status;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "ghostNet")
@Dependent
public class GhostNet {
    
    private int id;
    private LatLng location;
    private Size size;
    private Status status;
    private RegisteredUser reserved;
    
    /**
     * Creates a new instance of Netz
     */
    public GhostNet() {
    }

    public GhostNet(int id, LatLng location, Size size) {
        this.id = id;
        this.location = location;
        this.size = size;
    }
    
    public GhostNet(int id, LatLng location, Size size, Status status){
        this.id = id;
        this.location = location;
        this.size = size;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
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

    public RegisteredUser getReserved() {
        return reserved;
    }

    public void setReserved(RegisteredUser reserved) {
        this.reserved = reserved;
    }
    
}
