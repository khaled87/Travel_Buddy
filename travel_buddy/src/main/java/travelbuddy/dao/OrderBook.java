package travelbuddy.dao;

import travelbuddy.entity.PurchaseOrder;
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
        
    @PersistenceContext
    private EntityManager em;

    public OrderBook() {
        super(PurchaseOrder.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
