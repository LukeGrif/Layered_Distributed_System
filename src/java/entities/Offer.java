// entities/Offer.java

package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Freelancer freelancer;

    @ManyToOne
    private Job job;

    // New field to capture when the offer was created
    @Column(name = "offered_at", nullable = false)
    private LocalDateTime offeredAt;

    // --- existing getters/setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDateTime getOfferedAt() {
        return offeredAt;
    }

    public void setOfferedAt(LocalDateTime offeredAt) {
        this.offeredAt = offeredAt;
    }
}
