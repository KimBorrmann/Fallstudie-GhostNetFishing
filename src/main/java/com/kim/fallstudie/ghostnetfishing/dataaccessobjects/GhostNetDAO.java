/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.dataaccessobjects;

import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
public class GhostNetDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetdata");
    
    public List<GhostNet> findAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select a from GhostNet a");
        List<GhostNet> allGhostNets = query.getResultList();
        em.close();
        return allGhostNets;
    }
    
    public void saveNet(GhostNet newNet){
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        
        t.begin();
            em.merge(newNet);
        t.commit();
        
        em.close();
    }
}
