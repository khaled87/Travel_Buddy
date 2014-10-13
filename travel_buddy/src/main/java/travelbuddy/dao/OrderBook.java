package travelbuddy.dao;

import travelbuddy.entity.Customer;
import travelbuddy.entity.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All orders
 *
 * @author hajo
 */
@Stateless
public class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {

    protected OrderBook() {
        super(PurchaseOrder.class);
    }

    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        List<PurchaseOrder> found = new ArrayList<>();
        for (PurchaseOrder po : findRange(0, count())) {
            if (po.getCustomer().equals(c)) {
                found.add(po);
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
