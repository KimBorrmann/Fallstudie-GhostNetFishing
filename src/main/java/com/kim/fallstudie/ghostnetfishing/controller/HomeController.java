/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.controller;

import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import com.kim.fallstudie.ghostnetfishing.managedbean.Webapp;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Kim.Borrmann
 */
@Named
@ViewScoped
public class HomeController implements Serializable {
    @Inject
    private Webapp app;
    private GhostNet newNet = null;
    
    public GhostNet getNewNet() {
        if(newNet == null){
            this.newNet = new GhostNet();
        }
        return this.newNet;
    }
    
    public void addNewNet(){
        newNet = getNewNet();
    }
    
    public void handleCloseNewNetDialog(){
        newNet = null;
    }
    
    public void handleConfirmedNewNetDialog(){
        if(newNet != null){
            newNet.setStatus(Status.REPORTED);
            app.saveNet(newNet);
            handleCloseNewNetDialog();
        }
    }
}
