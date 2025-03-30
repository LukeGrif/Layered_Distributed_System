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
