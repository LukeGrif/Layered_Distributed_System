package services;

import entities.BaseUser;
import entities.Freelancer;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class UserService {
    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;

    public BaseUser findUserByUsernameAndPassword(String username, String password) {
        try {
            return em.createQuery(
                "SELECT u FROM BaseUser u WHERE u.username = :username AND u.password = :password", BaseUser.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Freelancer updateFreelancer(Freelancer f) {
    return em.merge(f);
  }
}
