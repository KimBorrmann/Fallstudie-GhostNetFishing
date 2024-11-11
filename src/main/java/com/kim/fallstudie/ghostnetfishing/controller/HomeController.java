/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.controller;

import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.enums.Size;
import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import com.kim.fallstudie.ghostnetfishing.managedbean.RegisteredUser;
import com.kim.fallstudie.ghostnetfishing.managedbean.Webapp;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "homeController")
@ViewScoped
public class HomeController implements Serializable {
    @Inject
    private Webapp app;
    
    @Inject
    private LoginController loginController;
    
    private GhostNet newNet;
    private GhostNet selectedNet;
    
    public GhostNet getNewNet() {
        if(newNet == null){
            this.newNet = new GhostNet();
            System.out.println("newNet set to new GhostNet()" + newNet);
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
    
    public void openNewNetDialog() {
        if(loginController.getCurrentUser() == null || loginController.getCurrentUser().getUsername() == null ){
            loginController.setCurrentUser(new RegisteredUser("Gast", "Gast")); //app.getRegisteredUsers().getFirst()
        }
        newNet = new GhostNet();
        System.out.println("Dialog geöffnet, newNet initialisiert: " + newNet);
    }
    
    public void openReserveNetDialog() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(loginController.getCurrentUser() == null || 
                loginController.getCurrentUser().getUsername() == null ||
                loginController.getCurrentUser().getUsername().equals("Gast")){
            facesContext.validationFailed();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation fehlgeschlagen!", "Sie müssen eingeloggt sein um Netze zu reservieren!"));
            try{
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            selectedNet = new GhostNet();
            System.out.println("Reserve Dialog geöffnet, selectedNet initialisiert: " + selectedNet);
        }
    }
    
    public void openRecoverNetDialog() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(loginController.getCurrentUser() == null || 
                loginController.getCurrentUser().getUsername() == null ||
                loginController.getCurrentUser().getUsername().equals("Gast")){
            facesContext.validationFailed();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation fehlgeschlagen!", "Sie müssen eingeloggt sein um Netze als Geborgen zu melden!"));
            try{
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Recover Dialog geöffnet");
        }
    }
    
    public void openLostNetDialog() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(loginController.getCurrentUser() == null || 
                loginController.getCurrentUser().getUsername() == null ||
                loginController.getCurrentUser().getUsername().equals("Gast")){
            facesContext.validationFailed();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation fehlgeschlagen!", "Sie müssen eingeloggt sein um Netze als Geborgen zu melden!"));
            try{
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Lost Dialog geöffnet");
        }
    }
    
    public void clearNewNet(){
        newNet = null;
        System.out.println("newNet null gesetzt");
    }
    
    public void handleConfirmedNewNetDialog(){
        System.out.println("handleConfirmedNewNetDialog called");
        if(newNet != null){
            newNet.setStatus(Status.REPORTED);
            newNet.setReportedBy(loginController.getCurrentUser());
            app.saveNet(newNet);
            clearNewNet();
        }
    }
    
    public void handleConfirmedReserveNetDialog(){
        System.out.println("Confirmed Reserve Net Dialog called");
        if(selectedNet != null){
            selectedNet.setStatus(Status.ALLOCATED);
            selectedNet.setRecoveredBy(loginController.getCurrentUser());
            app.saveNet(selectedNet);
            selectedNet = new GhostNet();
        }
    }
    
    public void handleConfirmedRecoverNetDialog(){
        if(selectedNet != null){
            selectedNet.setStatus(Status.RECOVERED);
            selectedNet.setRecoveredBy(loginController.getCurrentUser());
            app.saveNet(selectedNet);
            selectedNet = new GhostNet();
        }
    }
    
    public void handleConfirmedLostNetDialog(){
        if(selectedNet != null){
            selectedNet.setStatus(Status.LOST);
            app.saveNet(selectedNet);
            selectedNet = new GhostNet();
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
