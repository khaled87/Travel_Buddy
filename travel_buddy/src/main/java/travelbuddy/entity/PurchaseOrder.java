package travelbuddy.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A purchase order
 *
 * @author hajo
 */
@Entity
public class PurchaseOrder extends AbstractEntity {
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    @OneToOne
    private Product product;
    
    public PurchaseOrder() { } 
    
    public PurchaseOrder(Date date, Product product) {
        this.date = date;
        this.product = product;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + getDate()
                + ", product=" + getProduct() + "}";
    }

    public Date getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }
}
