/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.dataaccessobjects;

import com.kim.fallstudie.ghostnetfishing.managedbean.RegisteredUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author Kim.Borrmann
 */
public class UserDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetdata");
    
    public List<RegisteredUser> findAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select a from GhostNet a");
        List<RegisteredUser> allUsers = query.getResultList();
        em.close();
        return allUsers;
    }
}
