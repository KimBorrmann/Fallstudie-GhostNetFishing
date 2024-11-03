/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.enums;

/**
 *
 * @author Kim.Borrmann
 */
public enum Status{
    REPORTED("Gemeldet"),
    ALLOCATED("Reserviert"),
    RECOVERED("Geborgen"),
    LOST("Verschollen");
    
    private final String displayName;
    
    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
