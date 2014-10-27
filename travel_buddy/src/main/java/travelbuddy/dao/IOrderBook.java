
package travelbuddy.dao;

import javax.ejb.Local;
import travelbuddy.entity.*;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {
<<<<<<< HEAD
    public boolean anyOrderWithProductId(Long id);
=======

    public String createPurchaseOrder(PurchaseOrder po);
     public PurchaseOrder getPurchaseOrder(String confirmationCode);
    

>>>>>>> Confirmation
}