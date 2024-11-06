/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.controller;

import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import com.kim.fallstudie.ghostnetfishing.managedbean.Webapp;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
@Named
@ViewScoped
public class HomeController implements Serializable {
    @Inject
    private Webapp app;
    private GhostNet newNet;
    private GhostNet selectedNet;
    
    public GhostNet getNewNet() {
        if(newNet == null){
            this.newNet = new GhostNet();
        }
        return this.newNet;
    }

    public void setNewNet(GhostNet newNet) {
        this.newNet = newNet;
    }

    public GhostNet getSelectedNet() {
        if(selectedNet == null){
            this.selectedNet = new GhostNet();
        }
        return this.selectedNet;
    }

    public void setSelectedNet(GhostNet selectedNet) {
        this.selectedNet = selectedNet;
    }
    
    public GhostNet findById(int id){
        return app.getReportedNets().get(id);
    }
    
    public void openNewNetDialog() {
        newNet = new GhostNet();
        System.out.println("Dialog geöffnet, newNet initialisiert: " + newNet);
    }
    
    public void clearNewNet(){
        newNet = null;
        System.out.println("newNet null gesetzt");
    }
    
    public void handleConfirmedNewNetDialog(){
        if(newNet != null){
            newNet.setStatus(Status.REPORTED);
            newNet.setReserved(app.getCurrentUser());
            app.saveNet(newNet);
            System.out.println("newNet gespeichert: " + newNet);
            clearNewNet();
        }
    }
    
    public void handleConfirmedReserveNetDialog(){
        if(selectedNet != null){
            int id = selectedNet.getId();
            List<GhostNet> currentNets = app.getReportedNets();
            boolean netExists = currentNets.stream()
                    .anyMatch(net -> Integer.valueOf(net.getId()).equals(id));
            if(netExists){
                app.editNet(id, selectedNet);
                selectedNet = new GhostNet();
            }
        }
    }
    
    public List<SelectItem> getSizeOptions() {
        List<SelectItem> items = new ArrayList();
        for (Size size : Size.values()){
            items.add(new SelectItem(size, size.getDisplayName()));
        }
        return items;
    }
}
