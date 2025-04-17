package beans;

import entities.Job;
import entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;
import java.util.List;

@Named("currentJobsBean")
@RequestScoped
public class CurrentJobsBean {
  @Inject private LoginBean loginBean;
  @Inject private JobService jobService;

  public List<Job> getCurrentJobs() {
    User me = (User) loginBean.getLoggedInUser();
    return jobService.getCurrentJobsForFreelancer(me);
  }

//  public String complete(Job job) {
//    jobService.markJobAsCompleted(job);
//    return null;  // reload same page
//  }
}
