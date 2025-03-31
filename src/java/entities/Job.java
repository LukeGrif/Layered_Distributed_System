package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Job implements Serializable {

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Freelancer getAssignedFreelancer() {
        return assignedFreelancer;
    }

    public void setAssignedFreelancer(Freelancer assignedFreelancer) {
        this.assignedFreelancer = assignedFreelancer;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    private String title;
    private String keyword;
    
    @Lob
    private String description;

    private double paymentOffer;
    private String status; // "open", "closed", "completed"

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Freelancer assignedFreelancer;

    // Getters and Setters
}
