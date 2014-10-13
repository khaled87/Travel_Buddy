package travelbuddy.dao;

import travelbuddy.entity.IEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here (Create, Read,
 * Update, Delete = CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 * @param <T> Any type
 * @param <K> Key
 */
public abstract class AbstractDAO<T extends IEntity<K>, K>
        implements IDAO<T, K> {

    private final Class<T> clazz;

    protected abstract EntityManager getEntityManager();

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
    }

    @Override
    public void delete(K id) {
        T t = getEntityManager().getReference(clazz, id);
        getEntityManager().remove(t);
    }

    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return get(true, 0, 0);
    }

    @Override
    public List<T> findRange(int first, int n) {
        return get(false, first, n);
    }

    private List<T> get(boolean all, int first, int n) {
        List<T> found = new ArrayList<>();
        
        TypedQuery<T> q = getEntityManager().createQuery(String.format("select t from %1$s t", getTableName()), clazz);
        if (!all) {
            q.setFirstResult(first);
            q.setMaxResults(n);
        }
        found.addAll(q.getResultList());
        return found;
    }

    @Override
    public int count() {
        Long n = getEntityManager().createQuery(String.format("select count(t) from %1$s t", getTableName()), Long.class)
                .getSingleResult();
        return n.intValue();
    }
    
    protected String getTableName() {
        return clazz.getSimpleName();
    }
}
