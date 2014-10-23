
package travelbuddy.dao;

import travelbuddy.entity.PurchaseOrder;
import javax.ejb.Local;
import travelbuddy.entity.*;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {
    public PurchaseOrder book(Product product, TBUser user);
}