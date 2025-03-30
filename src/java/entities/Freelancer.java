package entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("freelancer")
public class Freelancer extends User {
    private String skills;
    @Column(length = 500)
    private String message;
    private double paymentBalance;

    // Getters and Setters
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(double paymentBalance) {
        this.paymentBalance = paymentBalance;
    }
}
