package travelbuddy.dao;

import javax.ejb.EJB;
import travelbuddy.entity.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import travelbuddy.common.SessionIdentifierGenerator;

/**
 * All orders
 *
 * @author hajo
 */
@Stateless
public class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {

    public OrderBook() {
        super(PurchaseOrder.class);
    }   
    
    @PersistenceContext
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private IUserRegistry userRegistry;
    
    @Override
    public PurchaseOrder book(Product product, TBUser user) {
        TBUser existingUser = userRegistry.getByEmail(user.getEmail());
        if (existingUser == null) {
            userRegistry.create(user);
            existingUser = user;
        }
        
        PurchaseOrder po = new PurchaseOrder();       
        po.setUser(existingUser);
        po.setProduct(product);
        po.setConfirmationCode(SessionIdentifierGenerator.nextSessionId());
        create(po);
        
        return po;
    }
}
