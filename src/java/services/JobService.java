package services;

import entities.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class JobService {
    private static final Logger LOGGER = Logger.getLogger(JobService.class.getName());

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
    
    public List<Job> getAllJobs() {
        return em.createQuery("SELECT j FROM Job j", Job.class)
                 .getResultList();
    }

    public void assignFreelancerToJob(Job job, Freelancer freelancer) {
        Job j = em.find(Job.class, job.getJobId());
        j.setAssignedFreelancer(freelancer);
        j.setStatus(2); // 2: in review
        em.merge(j);
        LOGGER.info(String.format(
            "Provider '%s' accepted freelancer '%s' for job %d",
            j.getProvider().getUsername(),
            freelancer.getUsername(),
            j.getJobId()
        ));
    }

    public void saveOffer(Offer offer) {
        em.persist(offer);
        LOGGER.info(String.format(
            "Freelancer '%s' offered to undertake job %d at %s",
            offer.getFreelancer().getUsername(),
            offer.getJob().getJobId(),
            offer.getOfferedAt()
        ));
    }
    
    public void updateJob(Job job) {
        em.merge(job);
    }
    
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
   
    public double getCompletedEarningsForFreelancer(Long freelancerId) {
        Double total = em.createQuery(
            "SELECT COALESCE(SUM(j.paymentOffer),0) " +
            "FROM Job j " +
            "WHERE j.assignedFreelancer.id = :fid " +
            "  AND j.status = 3",       // 3 = completed
            Double.class)
          .setParameter("fid", freelancerId)
          .getSingleResult();
        return total;
    }

    public void revokeJob(Job job) {
        Job managed = em.find(Job.class, job.getJobId());
        managed.setAssignedFreelancer(null);
        managed.setStatus(1);
        em.merge(managed);
    }

    public void completeJob(Job job) {
        Job managed = em.find(Job.class, job.getJobId());
        managed.setStatus(3);
        em.merge(managed);
        // Log completion
        Freelancer f = managed.getAssignedFreelancer();
        if (f != null) {
            LOGGER.info(String.format(
                "Job %d marked as completed; credited freelancer '%s' with %.2f tokens",
                managed.getJobId(),
                f.getUsername(),
                managed.getPaymentOffer()
            ));
        } else {
            LOGGER.warning(String.format(
                "Job %d marked as completed but no freelancer was assigned",
                managed.getJobId()
            ));
        }
    }
    
    public List<Offer> getOffersForFreelancer(Freelancer f) {
        return em.createQuery(
            "SELECT o FROM Offer o WHERE o.freelancer = :f", Offer.class)
          .setParameter("f", f)
          .getResultList();
    }
    
    public List<Job> findByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getOpenJobs();
        }
        String pattern = "%" + keyword.trim().toLowerCase() + "%";
        return em.createQuery(
                "SELECT j FROM Job j " +
                " WHERE j.status = 1 " +
                "   AND (LOWER(j.keyword) LIKE :pat)",
                Job.class
            )
            .setParameter("pat", pattern)
            .getResultList();
    }
    
    public Job findById(Long id) {
        if (id == null) return null;
        return em.find(Job.class, id);
    }
}
