/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.dataaccessobjects;

import com.kim.fallstudie.ghostnetfishing.enums.Status;
import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import com.kim.fallstudie.ghostnetfishing.managedbean.RegisteredUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
    
    public List<GhostNet> findAllNotRecovered(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<GhostNet> query = em.createQuery("SELECT g FROM GhostNet g WHERE g.status <> :status", GhostNet.class);
        query.setParameter("status", Status.RECOVERED);
        List<GhostNet> allNotRecoveredNets = query.getResultList();
        em.close();
        return allNotRecoveredNets;
    }
    
    public List<GhostNet> findRecovered(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<GhostNet> query = em.createQuery("SELECT g FROM GhostNet g WHERE g.status = :status", GhostNet.class);
        query.setParameter("status", Status.RECOVERED);
        List<GhostNet> allNotRecoveredNets = query.getResultList();
        em.close();
        return allNotRecoveredNets;
    }
    
    public GhostNet findById(int id){
        List<GhostNet> allNets = findAll();
        //boolean containsId = allNets.stream().anyMatch(ghostnet -> ghostnet.getId() == id);
        int index = -1;
            for(int i = 0; i< allNets.size(); i++){
                if(allNets.get(i).getId() == id){
                    index = i;
                    break;
                }
            }
            
        if(index != -1){
            GhostNet net = allNets.get(index);
            return net;
        }
        return null;
    }
    
    public void saveNet(GhostNet newNet){
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        
        t.begin();
            em.merge(newNet);
        t.commit();
        
        em.close();
    }
    
    public void deleteGhostNet(GhostNet net){
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        
        t.begin();
            //get net Entity into Context of EntityManager
            net = em.merge(net);
        t.commit();
        
        //Remove Associations
        RegisteredUser reportingUser = null;
        RegisteredUser recoveringUser = null;
        if (net.getReportedBy() != null) {
            int id = net.getReportedBy().getUserId();
            reportingUser = em.find(RegisteredUser.class, id);
            reportingUser.getReportedNets().remove(net);
        }
        if (net.getRecoveredBy() != null) {
            int id = net.getRecoveredBy().getUserId();
            recoveringUser = em.find(RegisteredUser.class, id);
            recoveringUser.getRecoveredNets().remove(net);
        }
        t.begin();
            //Update Users
            if(reportingUser != null){
               em.merge(reportingUser);
            }
            if(recoveringUser != null){
                em.merge(recoveringUser);
            }
            //remove net
            em.remove(net);
        t.commit();
        
        em.close();
    }
}
