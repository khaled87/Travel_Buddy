package travelbuddy.dao;

import travelbuddy.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * All products
 *
 * @author hajo
 */
@Stateless
public class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {

    protected ProductCatalogue() {
        super(Product.class);
    }

    // Factory method
    public static IProductCatalogue newInstance() {
        return new ProductCatalogue();
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> found = new ArrayList<>();
        
        TypedQuery<Product> q = getEntityManager().createQuery(
                String.format("select t from %1$s t where t.name like '%2$s'", getTableName(), name)
                , Product.class);
        found.addAll(q.getResultList());
        return found;
    }
        
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}