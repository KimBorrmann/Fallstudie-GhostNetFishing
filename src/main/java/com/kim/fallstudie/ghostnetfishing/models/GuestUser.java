/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.models;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author Kim.Borrmann
 */
@Named(value = "guestUser")
@Dependent
@Entity
@DiscriminatorValue("GUEST")
public class GuestUser extends User{

    /**
     * Creates a new instance of GuestUser
     */
    public GuestUser() {
        super(0, "Guest");
    }
    
}
