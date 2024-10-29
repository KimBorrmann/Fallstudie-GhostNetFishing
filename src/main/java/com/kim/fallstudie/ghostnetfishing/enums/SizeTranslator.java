/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.enums;

/**
 *
 * @author Kim.Borrmann
 */
public class SizeTranslator {
    public static String translate(Size size) {
        switch (size) {
            case VERY_SMALL:
                return "Sehr klein";
            case SMALL:
                return "Klein";
            case MEDIUM:
                return "Mittel";
            case LARGE:
                return "Groß";
            case VERY_LARGE:
                return "Sehr groß";
            default:
                return "";
        }
    }
}