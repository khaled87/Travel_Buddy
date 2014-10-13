package travelbuddy.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * A single row in an Order
 *
 * @author hajo
 */
@Entity
public class OrderItem extends AbstractEntity {
    private Product product;
    private int quantity;

    public OrderItem() {
        
    }
    
    public OrderItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }
    
    public OrderItem(Long id, Product product, int quantity) {
        super(id);
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }
}
