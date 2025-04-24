// src/main/java/beans/FindJobsBean.java
package beans;

import entities.Job;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import services.JobService;
import java.util.Collections;
import java.util.List;

@Named("findJobsBean")
@RequestScoped
public class FindJobsBean {
    @Inject 
    private JobService jobService;

    private String searchKeyword;
    private Job searchJob;            // bound via converter
    private List<Job> jobs;
  
    @PostConstruct
    public void init() {
        jobs = jobService.getOpenJobs();
    }

    /** keyword search */
    public void searchByKeyword() {
        jobs = jobService.findByKeyword(searchKeyword);
    }

    /** ID search */
    public void searchByJob() {
        if (searchJob == null) {
            jobs = jobService.getOpenJobs();
        } else {
            jobs = Collections.singletonList(searchJob);
        }
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Job getSearchJob() {
        return searchJob;
    }
    public void setSearchJob(Job searchJob) {
        this.searchJob = searchJob;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
