package services;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class JobService {

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;

    public void createJob(String title, String keyword, String description, double paymentOffer, Provider provider) {
        Job job = new Job();
        job.setTitle(title);
        job.setKeyword(keyword);
        job.setDescription(description);
        job.setPaymentOffer(paymentOffer);
        job.setStatus("open");
        job.setProvider(provider);

        em.persist(job);
    }

    public List<Job> getJobsByProvider(Provider provider) {
        return em.createQuery("SELECT j FROM Job j WHERE j.provider = :provider", Job.class)
                 .setParameter("provider", provider)
                 .getResultList();
    }

    public List<Job> getOpenJobs() {
        return em.createQuery("SELECT j FROM Job j WHERE j.status = 'open'", Job.class)
                 .getResultList();
    }

    public void assignFreelancerToJob(Job job, Freelancer freelancer) {
        Job j = em.find(Job.class, job.getJobId());
        j.setAssignedFreelancer(freelancer);
        j.setStatus("closed");
        em.merge(j);
    }

    public void markJobAsCompleted(Job job) {
        Job j = em.find(Job.class, job.getJobId());
        j.setStatus("completed");

        // Credit the freelancer
        Freelancer f = j.getAssignedFreelancer();
        if (f != null) {
            f.setPaymentBalance(f.getPaymentBalance() + j.getPaymentOffer());
            em.merge(f);
        }

        em.merge(j);
    }

    public Job findById(Long id) {
        return em.find(Job.class, id);
    }
    
    public void saveOffer(Offer offer) {
    em.persist(offer);
    }
}
