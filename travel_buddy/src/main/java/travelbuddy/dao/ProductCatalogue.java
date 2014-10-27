package travelbuddy.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import travelbuddy.entity.*;

/**
 * All products
 *
 * @author hajo
 */
@Stateless
public class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {

    protected ProductCatalogue() {
        super(Product.class);
    }

    // Factory method
    public static IProductCatalogue newInstance() {
        return new ProductCatalogue();
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> found = new ArrayList<>();

        TypedQuery<Product> q = getEntityManager().createQuery(
                String.format("select t from %1$s t where t.name like '%2$s'", getTableName(), name), Product.class);
        found.addAll(q.getResultList());
        return found;
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Product p) {
        if (p.getHotel() != null) {
            em.persist(p.getHotel());
        }
        if (p.getFlights() != null) {
            System.out.println("size of flight" + p.getFlights().size());
            for (Trip trip : p.getFlights()) {
                if (trip.getSubtripList() != null) {
                    for (SubTrip subTrip : trip.getSubtripList()) {
                        em.persist(subTrip);
                    }
                }
                em.persist(trip);
            }
        }
        super.create(p);
    }
    
    @Override
    public void delete(Long id) {
        Product p = find(id);
        if (p != null) {
            if (p.getHotel() != null) {
                em.remove(p.getHotel());
            }
            if (p.getFlights() != null) {
                for (Trip trip : p.getFlights()) {
                    em.remove(trip);
                    p.getFlights().remove(trip);
                }
            }
            super.delete(id);
        }
    }
}
