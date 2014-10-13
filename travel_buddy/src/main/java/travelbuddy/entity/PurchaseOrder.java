package travelbuddy.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A purchase order
 *
 * @author hajo
 */
@Entity
public class PurchaseOrder extends AbstractEntity {
    // State of order
    public enum State {
        ACCEPTED,
        CANCELLED,
        INVOICED,
        SHIPPED,
    }
    
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    @OneToMany
    @JoinColumn(name="ORDERITEM_FK")
    private List<OrderItem> items;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private State state = State.ACCEPTED;
    
    public PurchaseOrder() { } 

    public PurchaseOrder(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public PurchaseOrder(Long id, Customer customer, List<OrderItem> items) {
        super(id);
        this.customer = customer;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public State getState() {
        return state;
    }
    
     public void setState(PurchaseOrder.State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + date
                + ", items=" + items + ", customer=" + customer
                + ", state=" + state + '}';
    }
}
