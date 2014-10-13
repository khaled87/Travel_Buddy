package travelbuddy.dao;

import travelbuddy.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All customers
 *
 * @author hajo
 */
@Stateless
public class CustomerRegistry extends AbstractDAO<Customer, Long>
        implements ICustomerRegistry {

    protected CustomerRegistry() {
        super(Customer.class);
    }

    @Override
    public List<Customer> getByName(String name) {
        List<Customer> found = new ArrayList<>();
        for (Customer c : findRange(0, count())) {
            if (c.getFname().equals(name) || c.getLname().equals(name)) {
                found.add(c);
            }
        }
        return found;
    }
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
