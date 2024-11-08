/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.converter;

import com.kim.fallstudie.ghostnetfishing.dataaccessobjects.GhostNetDAO;
import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Kim.Borrmann
 */
@FacesConverter(forClass = GhostNet.class)
public class GhostNetConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value){
        if(value == null || value.isEmpty()){
            return null;
        }
        int id = Integer.parseInt(value);
        GhostNetDAO ghostNetDAO = context.getApplication().evaluateExpressionGet(context, "#{ghostNetDAO}", GhostNetDAO.class);
        return ghostNetDAO.findById(id);
    }
    
    @Override
    public String getAsString (FacesContext context, UIComponent component, Object value){
        if(value == null){
            return "";
        }
        if(value instanceof GhostNet){
            GhostNet ghostNet = (GhostNet) value;
            return String.valueOf(ghostNet.getId());
        }else{
            System.out.println("Unexpected object type: " + value.getClass().getName() + "value: " + value);
            throw new IllegalArgumentException("Object is not of type GhostNet");
        }
    }
}
