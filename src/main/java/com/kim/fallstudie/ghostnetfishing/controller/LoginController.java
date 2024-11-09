/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.controller;

import com.kim.fallstudie.ghostnetfishing.managedbean.RegisteredUser;
import com.kim.fallstudie.ghostnetfishing.managedbean.Webapp;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
@Named
@SessionScoped
public class LoginController implements Serializable{
    private String name;
    
    @Inject
    private RegisteredUser currentUser;
    
    @Inject
    private Webapp app;

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }
    
    public String login(){
        List<RegisteredUser> userList = app.getAllUsers();
        for (RegisteredUser b : userList){
            if(b.equals(this.currentUser))
                return "index.xhtml";
        }
        return "login.xhtml";
    }
    
    public void postValidateName(ComponentSystemEvent ev)throws AbortProcessingException {
        UIInput temp = (UIInput)ev.getComponent();
        this.name = (String)temp.getValue();
    }
    
    public void ValidateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        List<RegisteredUser> userList = app.getAllUsers();
        for (RegisteredUser b : userList) {
            RegisteredUser temp = new RegisteredUser(this.name, (String) value);
            if(b.equals(temp))
                return;
        }
        throw new ValidatorException(new FacesMessage("Login falsch!"));
    }
}
