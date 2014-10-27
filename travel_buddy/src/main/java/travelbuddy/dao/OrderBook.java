package travelbuddy.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import travelbuddy.entity.*;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import travelbuddy.common.EmailSender;
import travelbuddy.common.SessionIdentifierGenerator;

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

    @Override
    public String createPurchaseOrder(PurchaseOrder po) {
        po.setConfirmationCode(SessionIdentifierGenerator.nextSessionId());
        super.create(po);
        try {
            EmailSender.sendEmail(
                    "smtp.gmail.com",
                    "587",
                    "travel.buddy.org@gmail.com",
                    "banananana",
                    po.getUserEmail(),
                    "Booking confirmation for product: " + po.getProduct().getName(),
                    po.getConfirmationCode(),
                    null);
        } catch (MessagingException ex) {
            Logger.getLogger(OrderBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return po.getConfirmationCode();
    }

    @Override
    public PurchaseOrder getPurchaseOrder(String confirmationCode) {
        System.out.println("CONFIRMCODE " +confirmationCode );
        TypedQuery<PurchaseOrder> q = getEntityManager().createQuery("SELECT po FROM PurchaseOrder po WHERE po.confirmationCode =" +"'"+ confirmationCode +"'" , PurchaseOrder.class);
        System.out.println("titta" + q.getSingleResult());
        return q.getSingleResult();
    }
    
    @Override
    public boolean anyOrderWithProductId(Long id) {
        Long n = getEntityManager().createQuery(
                String.format("select count(t) from %1$s t where t.product.id = %2$s", getTableName(), id), Long.class)
                .getSingleResult();
        return n > 0;
    }
    
    @Override
    public boolean anyOrderWithProductId(Long id) {
        Long n = getEntityManager().createQuery(
                String.format("select count(t) from %1$s t where t.product.id = %2$s", getTableName(), id), Long.class)
                .getSingleResult();
        return n > 0;
    }
}
