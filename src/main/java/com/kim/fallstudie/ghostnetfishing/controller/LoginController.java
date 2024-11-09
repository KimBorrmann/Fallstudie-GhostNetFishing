/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.controller;

import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.UserDAO;
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
    
    private RegisteredUser currentUser = new RegisteredUser();
    private RegisteredUser newUser = new RegisteredUser();
    
    @Inject
    private Webapp app;
    
    private final UserDAO userDAO;
    
    public LoginController(){
        userDAO = new UserDAO();
    }

    public RegisteredUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(RegisteredUser currentUser) {
        this.currentUser = currentUser;
    }

    public RegisteredUser getNewUser() {
        return newUser;
    }

    public void setNewUser(RegisteredUser newUser) {
        this.newUser = newUser;
    }
    
    public String login(){
        List<RegisteredUser> userList = app.getAllUsers();
        for (RegisteredUser b : userList){
            if(b.equals(this.currentUser))
                return "index.xhtml";
        }
        return "login.xhtml";
    }
    
    public String continueWithoutLogin(){
        setCurrentUser(new RegisteredUser("Gast", "Gast"));
        return "index.xhtml";
    }
    
    public String logout(){
        currentUser = new RegisteredUser("Gast","Gast");
        return "login.xhmtl";
    }
    
    public void postValidateName(ComponentSystemEvent ev)throws AbortProcessingException {
        UIInput temp = (UIInput)ev.getComponent();
        this.name = (String)temp.getValue();
    }
    
    public void ValidateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        List<RegisteredUser> userList = app.getAllUsers();
        for (RegisteredUser b : userList) {
            RegisteredUser temp = new RegisteredUser(this.name, (String) value);
            if(b.equals(temp)){
                setCurrentUser(b);
                return;
            } 
        }
        throw new ValidatorException(new FacesMessage("Login falsch!"));
    }
    
    public void validateNewName(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        List<RegisteredUser> userList = app.getAllUsers();
        for (RegisteredUser b : userList){
            String existingName = b.getUsername();
            String newName = (String) value;
            if(existingName.equals(newName)){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername existiert bereits!", "Name: " + newName + "ist bereits vohanden, bitte wähle einen anderen."));
            }
        }
    }
    
    public void validateTelephoneNumber(FacesContext context, UIComponent component, String value) throws ValidatorException {
        // Regular expression for validating telephone numbers
        String regex = "^\\+?[0-9. ()-]{7,15}$";
        String phoneNumber = (String) value;
        
        if (!phoneNumber.matches(regex)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validierung fehlgeschlagen!", "Ungültige Telefonnummer!"));
        }
    }
    
    public void createAccount() throws ValidatorException {
        if(newUser.getUsername().equals("") || newUser.getTelephone().equals("") || newUser.getPassword().equals("")){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validierung fehlgeschlagen!", ""));
        }else{
            System.out.println("id: " + newUser.getUserId() + " name: " + newUser.getUsername());
            userDAO.saveUser(newUser);
            System.out.println("Saved new User: " + newUser);
            newUser = new RegisteredUser();
        }
    }
}
