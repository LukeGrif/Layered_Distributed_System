package beans;

import entities.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;
import java.io.Serializable;

import java.util.List;

@Named("browseJobsBean")
@RequestScoped  
public class BrowseJobsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private JobService jobService;
    @Inject
    private LoginBean loginBean;
    
    private Job selectedJob;

    public List<Job> getOpenJobs() {
        return jobService.getOpenJobs();
    }

    public String selectJob(Job job) {
        Freelancer me = (Freelancer) loginBean.getLoggedInUser();
        jobService.assignFreelancerToJob(job, me);
        return "freelancerCurrentJobs.xhtml?faces-redirect=true";
      }

    public String offerForJob(Job job) {
        Freelancer freelancer = (Freelancer) loginBean.getLoggedInUser();

        Offer offer = new Offer();
        offer.setJob(job);
        offer.setFreelancer(freelancer);
        //offer.setOfferedAt(LocalDateTime.now());

        //job.getProvider().getUsername(); // optional for display
        jobService.saveOffer(offer);

        return "freelancerCurrentJobs.xhtml?faces-redirect=true";
    }
    
    public List<Job> getCurrentJobsForFreelancer() {
        Freelancer me = (Freelancer) loginBean.getLoggedInUser();
        return jobService.getCurrentJobsForFreelancer(me);
    }

    // Getters and Setters
    public Job getSelectedJob() { 
        return selectedJob; 
    
    }
    public void setSelectedJob(Job selectedJob) { 
        this.selectedJob = selectedJob; 
    }
    

  public String completeJob(Job job) {
    // mark as “completed”
    jobService.completeJob(job);
    return "freelancerCurrentJobs.xhtml?faces-redirect=true";
  }
}
