
package travelbuddy.dao;

import travelbuddy.entity.Customer;
import travelbuddy.entity.PurchaseOrder;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {

    List<PurchaseOrder> getByCustomer(Customer c);
    
}