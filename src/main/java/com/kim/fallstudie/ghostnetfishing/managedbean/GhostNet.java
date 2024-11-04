/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.managedbean;

import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.enums.Status;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import java.util.Random;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "ghostNet")
//@Dependent
public class GhostNet {
    
    private int id;
    private double latitude;
    private double longitude;
    private Size size;
    private Status status;
    private RegisteredUser reserved;
    
    /**
     * Creates a new instance of Netz
     */
    public GhostNet() {
        this.id = generateRandomId();
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.size = null;
        this.status = null;
        this.reserved = null;
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
    
    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt(10000);
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

    public RegisteredUser getReserved() {
        return reserved;
    }

    public void setReserved(RegisteredUser reserved) {
        this.reserved = reserved;
    }
    
}
