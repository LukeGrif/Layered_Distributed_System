package beans;

import entities.Job;
import entities.Provider;
import entities.Admin;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class JobBean implements Serializable {

    private String title;
    private String keyword;
    private String description;
    private double paymentOffer;

    private List<Job> providerJobs;
    private List<Job> allJobs;

    @Inject
    private JobService jobService;

    @Inject
    private LoginBean loginBean; // we assume this has the logged-in user

    public String postJob() {
        Provider provider = (Provider) loginBean.getLoggedInUser();
        jobService.createJob(title, keyword, description, paymentOffer, provider);
        clearForm();
        return "providerDashboard.xhtml?faces-redirect=true";
    }

    public List<Job> getProviderJobs() {
        Provider provider = (Provider) loginBean.getLoggedInUser();
        return jobService.getJobsByProvider(provider);
    }
    
    public List<Job> getAllJobs() {
        Admin admin = (Admin) loginBean.getLoggedInUser();
        return jobService.getAllJobs();
    }

    private void clearForm() {
        title = "";
        keyword = "";
        description = "";
        paymentOffer = 0.0;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPaymentOffer() {
        return paymentOffer;
    }

    public void setPaymentOffer(double paymentOffer) {
        this.paymentOffer = paymentOffer;
    }

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
