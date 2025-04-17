package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "JOBS")
public class Job implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long jobId;            
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    private String keyword;
    @Column(name = "PAYMENT_OFFER")
    private Double paymentOffer;
    @Column(name = "STATUS")
    private Integer status;
    
//    @Lob
//    private String description;
//    private double paymentOffer;
//    private int status; // 1:"open", 2:"In Review",3:"Closed ,4:"completed"

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "assigned_freelancer_id")
    private Freelancer assignedFreelancer;
    
    public Job() { }
    
    public Job(String title, String description, String keyword, Double paymentOffer, Integer status) {
        this.title = title;
        this.description = description;
        this.keyword = keyword;
        this.paymentOffer = paymentOffer;
        this.status = status;
    }
    
 
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

    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
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
  

    // Getters and Setters
    
    public String getStatusLabel() {
        switch (status) {
            case 1: return "Open";
            case 2: return "In Review";
            case 3: return "Assigned";
            case 4: return "Completed";
            default: return "Unknown";
}
    }
}
