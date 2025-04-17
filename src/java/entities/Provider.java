package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("provider")
public class Provider extends BaseUser {
    // Can add any additional provider-specific fields later
}
