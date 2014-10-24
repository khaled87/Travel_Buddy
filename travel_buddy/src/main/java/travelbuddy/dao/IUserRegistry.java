
package travelbuddy.dao;

import travelbuddy.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface to product catalogue
 * @author hajo
 */
@Local
public interface IUserRegistry extends IDAO<TBUser, Long> {

    public List<TBUser> getByName(String name);
    public TBUser getByEmail(String email);
    public TBUser login(String auth);
    public TBUser login(String name, String password);
    
}
