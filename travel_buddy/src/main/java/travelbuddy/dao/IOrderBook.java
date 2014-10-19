
package travelbuddy.dao;

import travelbuddy.entity.PurchaseOrder;
import javax.ejb.Local;

/**
 * Interface to order book
 * @author hajo
 */
@Local
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {

    
}