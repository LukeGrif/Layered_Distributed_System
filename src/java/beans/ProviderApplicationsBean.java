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
    private LoginBean loginBean;            // gives us the logged‑in user

    @Inject
    private JobService jobService;          // you already have this EJB

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;               // used for a simple JPQL query

    private List<Offer> applications;       // list shown in the table

    /** Load pending offers for this provider’s jobs (status == 1). */
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

    /** Getter for JSF `<h:dataTable>` binding. */
    public List<Offer> getApplications() {
        return applications;
    }

    /** Accept a freelancer’s offer. */
    public void accept(Offer offer) {
        // Re‑use existing service method
        jobService.assignFreelancerToJob(
            offer.getJob(),
            offer.getFreelancer()
        );

        // Remove from list so table refreshes on next render
        applications.remove(offer);
    }
}
