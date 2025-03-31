package beans;

import entities.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@SessionScoped
public class BrowseJobsBean implements Serializable {

    private Job selectedJob;

    @Inject
    private JobService jobService;

    @Inject
    private LoginBean loginBean;

    public List<Job> getOpenJobs() {
        return jobService.getOpenJobs();
    }

    public String selectJob(Job job) {
        this.selectedJob = job;
        return "jobDetails.xhtml?faces-redirect=true";
    }

    public String offerForJob() {
        Freelancer freelancer = (Freelancer) loginBean.getLoggedInUser();

        Offer offer = new Offer();
        offer.setJob(selectedJob);
        offer.setFreelancer(freelancer);
        offer.setOfferedAt(LocalDateTime.now());

        selectedJob.getProvider().getUsername(); // optional for display
        jobService.saveOffer(offer);

        return "freelancerHome.xhtml?faces-redirect=true";
    }

    // Getters and Setters
    public Job getSelectedJob() { return selectedJob; }
    public void setSelectedJob(Job selectedJob) { this.selectedJob = selectedJob; }
}
