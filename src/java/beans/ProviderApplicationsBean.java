package beans;

import entities.Offer;
import entities.Provider;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import services.JobService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("providerApplicationsBean")
@RequestScoped
public class ProviderApplicationsBean implements Serializable {

    @Inject
    private LoginBean loginBean;            // gives logged in user

    @Inject
    private JobService jobService;          

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;            

    private List<Offer> applications;       

    /** Loads offers only if the (status == 1). */
    @PostConstruct
    public void init() {
        Object user = loginBean.getLoggedInUser();
        if (user instanceof Provider) {
            Provider prov = (Provider) user;

            applications = em.createQuery(
                "SELECT o FROM Offer o " +
                "WHERE o.job.provider = :prov AND o.job.status = 1",
                Offer.class)
              .setParameter("prov", prov)
              .getResultList();
        } else {
            applications = new ArrayList<>();
        }
    }

   
    public List<Offer> getApplications() {
        return applications;
    }

    
    public void accept(Offer offer) {
        jobService.assignFreelancerToJob(
            offer.getJob(),
            offer.getFreelancer()
        );

        applications.remove(offer);
    }
}
