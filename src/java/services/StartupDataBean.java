//package services;
//
//import entities.Freelancer;
//import entities.Provider;
//import jakarta.annotation.PostConstruct;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.persistence.*;
//
//@Startup
//@Singleton
//public class StartupDataBean {
//
//    @PersistenceContext(unitName = "MarketPlaceDS")
//    private EntityManager em;
//
//    //@PostConstruct
//    public void init() {
//        System.out.println("StartupDataBean initializing...");
//
//        try {
//            Long count = em.createQuery("SELECT COUNT(u) FROM BaseUser u", Long.class).getSingleResult();
//            System.out.println("🔍 Found " + count + " users.");
//
//            if (count == 0) {
//                System.out.println("Creating test users...");
//
//                Provider provider = new Provider();
//                provider.setUsername("provider1");
//                provider.setPassword("pass123");
//                provider.setName("John Provider");
//                em.persist(provider);
//
//                Freelancer freelancer = new Freelancer();
//                freelancer.setUsername("freelancer1");
//                freelancer.setPassword("pass123");
//                freelancer.setName("Jane Freelancer");
//                freelancer.setSkills("Java, JSF, SQL");
//                freelancer.setMessage("Experienced developer ready to help!");
//                freelancer.setPaymentBalance(0.0);
//                em.persist(freelancer);
//            }
//        } catch (Exception e) {
//            System.out.println("StartupDataBean error: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//        long count = em.createQuery("SELECT COUNT(j) FROM Job j", Long.class)
//                       .getSingleResult();
//        if (count > 0) {
//        return;        
//    }
//        
//    }
//}
//
//
