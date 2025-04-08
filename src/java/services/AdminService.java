/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class AdminService {

    @PersistenceContext(unitName = "MarketPlaceDS")
    EntityManager em;

    public void registerFreelancer(Freelancer freelancer) {
        em.persist(freelancer);
    }

    public void removeFreelancer(Long id) {
        Freelancer f = em.find(Freelancer.class, id);
        if (f != null) em.remove(f);
    }

    public void registerProvider(Provider provider) {
        em.persist(provider);
    }

    public void removeProvider(Long id) {
        Provider p = em.find(Provider.class, id);
        if (p != null) em.remove(p);
    }

    public void removeJob(Long jobId) {
        Job job = em.find(Job.class, jobId);
        if (job != null) em.remove(job);
    }
}
