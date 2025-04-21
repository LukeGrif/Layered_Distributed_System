package startup;

import entities.Admin;
import entities.Freelancer;
import entities.Provider;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

//@Startup
@Singleton
public class UserSeederBean {

    @PersistenceContext(unitName = "MarketPlaceDS")
    EntityManager em;

    @PostConstruct
    public void init() {
        insertIfNotExists("admin1", "admin", Admin.class);
        insertIfNotExists("provider1", "provider", Provider.class);
        insertIfNotExists("freelancer1", "freelancer", Freelancer.class);
    }

    private void insertIfNotExists(String username, String role, Class<?> clazz) {
        try {
            em.createQuery("SELECT u FROM BaseUser u WHERE u.username = :username")
              .setParameter("username", username)
              .getSingleResult();
            System.out.println(" User " + username + " already exists.");
        } catch (NoResultException e) {
            if (clazz == Admin.class) {
                Admin a = new Admin();
                a.setUsername(username);
                a.setPassword("pass123");
                a.setName("Admin Guy");
                em.persist(a);
            } else if (clazz == Provider.class) {
                Provider p = new Provider();
                p.setUsername(username);
                p.setPassword("pass123");
                p.setName("Test Provider");
                em.persist(p);
            } else if (clazz == Freelancer.class) {
                Freelancer f = new Freelancer();
                f.setUsername(username);
                f.setPassword("pass123");
                f.setName("Test Freelancer");
                f.setSkills("Java, JSF, SQL");
                f.setMessage("Ready to work!");
                f.setPaymentBalance(0.0);
                em.persist(f);
            }
            System.out.println("Created test user: " + username);
        }
    }
}
