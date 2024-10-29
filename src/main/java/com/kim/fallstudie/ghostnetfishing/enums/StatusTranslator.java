/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.enums;

/**
 *
 * @author Kim.Borrmann
 */
public class StatusTranslator {
    public static String translate(Status status) {
        switch (status) {
            case REPORTED:
                return "Gemeldet";
            case ALLOCATED:
                return "Zugewiesen";
            case RECOVERED:
                return "Geborgen";
            case LOST:
                return "Verschollen";
            default:
                return "";
        }
    }
}
