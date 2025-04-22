package services;

import entities.Offer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class OfferService {

    @PersistenceContext(unitName = "MarketPlaceDS")
    private EntityManager em;

    /**  
     * deletes this Offer from the database.  
     */
    public void revokeOffer(Offer offer) {
        Offer managed = em.find(Offer.class, offer.getId());
        if (managed != null) {
            em.remove(managed);
        }
    }
}
