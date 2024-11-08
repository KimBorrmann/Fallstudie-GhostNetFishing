/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kim.fallstudie.ghostnetfishing.dataaccessobjects;

import com.kim.fallstudie.ghostnetfishing.managedbean.GhostNet;
import com.kim.fallstudie.ghostnetfishing.managedbean.RegisteredUser;
import com.kim.fallstudie.ghostnetfishing.models.User;
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
public class UserDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetdata");
    
    public List<RegisteredUser> findAllRegistered(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select a from User a where Type(a) = RegisteredUser");
        List<RegisteredUser> registeredUsers = query.getResultList();
        em.close();
        return registeredUsers;
    }
    
    public List<User> findAllUsers(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select a from User a");
        List<User> allUsers = query.getResultList();
        em.close();
        return allUsers;
    }
    
    public void saveUser(User newUser){
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        
        t.begin();
            em.merge(newUser);
        t.commit();
        
        em.close();
    }
}
