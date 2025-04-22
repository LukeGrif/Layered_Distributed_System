package beans;

import entities.Job;
import entities.BaseUser;
import entities.Freelancer;
import entities.Offer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;
import services.OfferService;
import java.util.List;

@Named("currentJobsBean")
@RequestScoped
public class CurrentJobsBean {
  @Inject private LoginBean loginBean;
  @Inject private JobService jobService;
  @Inject private OfferService offerService;

    public List<Job> getCurrentJobs() {
        BaseUser me = (BaseUser) loginBean.getLoggedInUser();
        return jobService.getCurrentJobsForFreelancer(me);
      }
    
    public List<Offer> getMyApplications() {
        Freelancer me = (Freelancer) loginBean.getLoggedInUser();
        return jobService.getOffersForFreelancer(me);
      }

    public String revokeOffer(Offer offer){
        offerService.revokeOffer(offer);
        return null;
    }

    public String complete(Job job) {
            jobService.completeJob(job);
            return null;
        }

}
