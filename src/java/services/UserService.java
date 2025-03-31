package services;

import entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;

    public User login(String username, String password, String role) {
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);

            User user = query.getSingleResult();

            // Check discriminator value (freelancer/provider/admin)
            if (em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(user) != null &&
                user.getClass().getAnnotation(DiscriminatorValue.class).value().equals(role)) {
                return user;
            }

            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void register(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
