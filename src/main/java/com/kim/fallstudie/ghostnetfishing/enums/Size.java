/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.enums;

/**
 *
 * @author Kim.Borrmann
 */
public enum Size{
    VERY_SMALL("Sehr klein"),
    SMALL("Klein"),
    MEDIUM("Mittel"),
    LARGE("Groß"),
    VERY_LARGE("Sehr groß");

    private final String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}