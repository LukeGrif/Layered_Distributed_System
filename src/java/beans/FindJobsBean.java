package beans;

import entities.Job;
import jakarta.annotation.PostConstruct;

import services.JobService;
import java.util.Collections;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("findJobsBean")
@RequestScoped
public class FindJobsBean {
  @Inject private JobService jobService;

  /** bound to search box */
  private String searchKeyword;
  private List<Job> jobs;
  
@PostConstruct
public void init() {
  // show all open jobs by default
  jobs = jobService.getOpenJobs();
}


  public void search() {
    jobs = jobService.findByKeyword(searchKeyword);
  }

  // getters & setters
  public String getSearchKeyword() { 
      return searchKeyword; 
  }
  
  public void setSearchKeyword(String searchKeyword) { 
      this.searchKeyword = searchKeyword; 
  }
  
  public List<Job> getJobs() {
      return jobs; 
  }
  
}
