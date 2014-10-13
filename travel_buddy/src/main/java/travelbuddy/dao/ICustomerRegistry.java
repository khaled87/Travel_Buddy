package travelbuddy.dao;

import travelbuddy.entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to customer registry
 * @author hajo
 */
@Local
public interface ICustomerRegistry extends IDAO<Customer, Long> {

    List<Customer> getByName(String name);
    
}
