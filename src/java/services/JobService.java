package services;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class JobService {
    
    @Inject
    private JobService jobService;
    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;


    public void createJob(String title, String keyword, String description, double paymentOffer, Provider provider) {
        Job job = new Job();
        job.setTitle(title);
        job.setKeyword(keyword);
        job.setDescription(description);
        job.setPaymentOffer(paymentOffer);
        job.setStatus(1);
        job.setProvider(provider);

        em.persist(job);
    }

    public List<Job> getJobsByProvider(Provider provider) {
        return em.createQuery("SELECT j FROM Job j WHERE j.provider = :provider", Job.class)
                 .setParameter("provider", provider)
                 .getResultList();
    }

    public List<Job> getOpenJobs() {
        return em.createQuery("SELECT j FROM Job j WHERE j.status = 1", Job.class)
                 .getResultList();
    }

    public void assignFreelancerToJob(Job job, Freelancer freelancer) {
        Job j = em.find(Job.class, job.getJobId());
        j.setAssignedFreelancer(freelancer);
        j.setStatus(2); // 2: in review
        em.merge(j);
    }

//    public void markJobAsCompleted(Job job) {
//        Job j = em.find(Job.class, job.getJobId());
//        j.setStatus(4);
//
//        // Credit the freelancer
//        Freelancer f = j.getAssignedFreelancer();
//        if (f != null) {
//            f.setPaymentBalance(f.getPaymentBalance() + j.getPaymentOffer());
//            em.merge(f);
//        }
//
//        em.merge(j);
//    }

    public Job findById(Long id) {
        return em.find(Job.class, id);
    }
    
    public void saveOffer(Offer offer) {
    em.persist(offer);
    }
    
    public void updateJob(Job job) {
    em.merge(job);
    }
    
    // in JobService.java
    public List<Job> getJobsForFreelancer(Freelancer f) {
        return em.createQuery(
            "SELECT j FROM Job j WHERE j.assignedFreelancer = :f", Job.class)
          .setParameter("f", f)
          .getResultList();
    }
    
    public List<Job> getCurrentJobsForFreelancer(BaseUser freelancer) {
        return em.createQuery(
          "SELECT j FROM Job j "
            + "WHERE j.assignedFreelancer = :f "
            + "  AND j.status IN (2,3,4)", Job.class)
          .setParameter("f", freelancer)
          .getResultList();
  }
    
    public void revokeJob(Job job) {
    Job managed = em.find(Job.class, job.getJobId());
    // un-assign the freelancer and reset to “open”
    managed.setAssignedFreelancer(null);
    managed.setStatus(1);
    // JPA will auto-flush on transaction commit
  }

  public void completeJob(Job job) {
    Job managed = em.find(Job.class, job.getJobId());
    managed.setStatus(4);
  }
      
}
