package beans;

import entities.Freelancer;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import services.JobService;
import services.UserService;

@Named("profileBean")
@ViewScoped
public class profileBean implements Serializable {

    @Inject private LoginBean loginBean;
    @Inject private JobService jobService;
     @Inject private UserService userService;

    public double getPaymentBalance() {
        Freelancer me = (Freelancer) loginBean.getLoggedInUser();
        return jobService.getCompletedEarningsForFreelancer(me.getId());
    }
    
    private Freelancer freelancer;

    @PostConstruct
    public void init() {
      // pull the session’s user and cast to Freelancer
      this.freelancer = (Freelancer) loginBean.getLoggedInUser();
    }

    /** Called by the “Update” button */
    public String update() {
      // merge
      Freelancer merged = userService.updateFreelancer(freelancer);
      loginBean.setLoggedInUser(merged);
      return "freelancerProfile?faces-redirect=true";
    }

    public Freelancer getFreelancer() {
      return freelancer;
    }
    public void setFreelancer(Freelancer f) {
      this.freelancer = f;
    }
}
