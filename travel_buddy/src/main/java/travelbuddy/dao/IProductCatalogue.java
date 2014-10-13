
package travelbuddy.dao;

import travelbuddy.entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to product catalogue
 * @author hajo
 */
@Local
public interface IProductCatalogue extends IDAO<Product, Long> {

    public List<Product> getByName(String name);
     
}
