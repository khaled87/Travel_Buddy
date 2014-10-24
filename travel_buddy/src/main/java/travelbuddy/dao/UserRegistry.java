package travelbuddy.dao;

import java.util.ArrayList;
import java.util.List;
import travelbuddy.entity.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.DatatypeConverter;

/**
 * All orders
 *
 * @author hajo
 */
@Stateless
public class UserRegistry extends AbstractDAO<TBUser, Long>
        implements IUserRegistry {
        
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserRegistry() {
        super(TBUser.class);
    }
    
    @Override
    public List<TBUser> getByName(String name) {
        List<TBUser> found = new ArrayList<>();
        
        TypedQuery<TBUser> q = getEntityManager().createQuery(
                String.format("select t from %1$s t where t.name like '%2$s'", getTableName(), name)
                , TBUser.class);
        found.addAll(q.getResultList());
        return found;
    }
    
    @Override
    public TBUser getByEmail(String email) {
        List<TBUser> found = new ArrayList<>();
        
        TypedQuery<TBUser> q = getEntityManager().createQuery(
                String.format("select t from %1$s t where t.email like '%2$s'", getTableName(), email)
                , TBUser.class);
        found.addAll(q.getResultList());
        if (found.isEmpty()) {
            return null;
        } else {
            return found.get(0);
        }
    }
    
    @Override
    public TBUser login(String name, String password) {
        List<TBUser> found = new ArrayList<>();
        
        TypedQuery<TBUser> q = getEntityManager().createQuery(
                String.format("select t from %1$s t where upper(t.name) = upper('%2$s') and t.password = '%3$s'", getTableName(), name, password)
                , TBUser.class);
        found.addAll(q.getResultList());
        if (found.isEmpty()) {
            return null;
        } else {
            return found.get(0);
        }
    }
    
    @Override
    public TBUser login(String auth) {
        byte[] base64 = DatatypeConverter.parseBase64Binary(auth.split(" ")[1].trim());
        String credentials = new String(base64);

        String[] cr = credentials.split(":");
        if (cr.length != 2) {
            return null;
        }
        return login(cr[0], cr[1]);
    }
}
