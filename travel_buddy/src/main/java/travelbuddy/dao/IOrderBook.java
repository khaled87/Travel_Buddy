
package travelbuddy.dao;

import javax.ejb.Local;
import travelbuddy.entity.*;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {
    public boolean anyOrderWithProductId(Long id);
<<<<<<< HEAD
    public String createPurchaseOrder(PurchaseOrder po);
    public PurchaseOrder getPurchaseOrder(String confirmationCode);
=======
>>>>>>> origin/master
}