package beans;

import entities.Freelancer;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import services.JobService;

@Named("profileBean")
@ViewScoped
public class profileBean implements Serializable {

    @Inject private LoginBean loginBean;
    @Inject private JobService jobService;

    public double getPaymentBalance() {
        Freelancer me = (Freelancer) loginBean.getLoggedInUser();
        return jobService.getCompletedEarningsForFreelancer(me.getId());
    }
}
