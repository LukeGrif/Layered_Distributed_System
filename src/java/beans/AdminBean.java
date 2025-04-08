/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import entities.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import services.AdminService;

@Named
@SessionScoped
public class AdminBean implements Serializable {

    @Inject
    private AdminService adminService;

    private Freelancer freelancer = new Freelancer();
    private Provider provider = new Provider();
    
    private Long freelancerIdToRemove;
    private Long providerIdToRemove;
    private Long jobIdToRemove;
    
    // Register freelancer
    public String registerFreelancer() {
        adminService.registerFreelancer(freelancer);
        freelancer = new Freelancer(); // reset form
        return "/admin/adminHome.xhtml?faces-redirect=true";
    }

    // Remove freelancer
    public String removeFreelancer() {
        adminService.removeFreelancer(freelancerIdToRemove);
        freelancerIdToRemove = null;
        return "/admin/adminHome.xhtml?faces-redirect=true";
    }
    
        // Register provider
    public String registerProvider() {
        adminService.registerProvider(provider);
        provider = new Provider(); // reset form
        return "/admin/adminHome.xhtml?faces-redirect=true";
    }

    // Remove provider
    public String removeProvider() {
        adminService.removeProvider(providerIdToRemove);
        providerIdToRemove = null;
        return "/admin/adminHome.xhtml?faces-redirect=true";
    }

    // Remove job
    public String removeJob() {
        adminService.removeJob(jobIdToRemove);
        jobIdToRemove = null;
        return "/admin/adminHome.xhtml?faces-redirect=true";
    }

    // Getters and Setters
    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Long getFreelancerIdToRemove() {
        return freelancerIdToRemove;
    }

    public void setFreelancerIdToRemove(Long freelancerIdToRemove) {
        this.freelancerIdToRemove = freelancerIdToRemove;
    }

    public Long getProviderIdToRemove() {
        return providerIdToRemove;
    }

    public void setProviderIdToRemove(Long providerIdToRemove) {
        this.providerIdToRemove = providerIdToRemove;
    }

    public Long getJobIdToRemove() {
        return jobIdToRemove;
    }

    public void setJobIdToRemove(Long jobIdToRemove) {
        this.jobIdToRemove = jobIdToRemove;
    }
}
