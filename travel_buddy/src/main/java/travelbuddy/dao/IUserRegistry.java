
package travelbuddy.dao;

import travelbuddy.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
* UserRegistry is a DAO to handle user manipulation (CRUD) and login
*/
@Local
public interface IUserRegistry extends IDAO<TBUser, Long> {

    public List<TBUser> getByName(String name);
    public TBUser getByEmail(String email);
    /**
    * Log user in the system
    * @param auth encoded authentication credentials
    * @return user having the provided credentials and null if not found.
    */
    public TBUser login(String auth);
    public TBUser login(String name, String password);
    
}
