package services;

import entities.BaseUser;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;

    public BaseUser login(String username, String password, String role) {
        try {
            TypedQuery<BaseUser> query = em.createQuery("SELECT u FROM BaseUser u WHERE u.username = :username AND u.password = :password",
                BaseUser.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);

            BaseUser user = query.getSingleResult();

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

    public void register(BaseUser user) {
        em.persist(user);
    }

    public BaseUser findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM BaseUser u WHERE u.username = :username", BaseUser.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
