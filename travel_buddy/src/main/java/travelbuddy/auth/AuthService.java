package travelbuddy.auth;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.dao.IUserRegistry;
import travelbuddy.entity.TBUser;

@Path("/auth")
public class AuthService {
    @EJB
    private IUserRegistry userRegistry;
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(String s) {
        TBUser user = userRegistry.login(s);
        if (user != null) {
            return Response.ok(new GenericEntity<TBUser>(user) { }).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
