package travelbuddy.webservice;

import javax.ejb.EJB;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import travelbuddy.dao.IUserRegistry;
import travelbuddy.entity.TBUser;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @EJB
    private IUserRegistry userRegistry;
    
    @Context
    SecurityContext sCtx;

    @Override
    public void filter(ContainerRequestContext rCtx) throws WebApplicationException {
        UriInfo uriInfo = rCtx.getUriInfo();

        if (isAuthorizationRequired(uriInfo)) {
            boolean isAuthorized = false;
            String auth = rCtx.getHeaderString("authorization");
            TBUser user = userRegistry.login(auth);
            if (auth != null && user != null && user.getRole().equals(TBUser.Role.ADMIN)) {
                isAuthorized = true;
            }

            if (!isAuthorized) {
                rCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("No authorization").build());
            }
        }
    }

    private static boolean isAuthorizationRequired(UriInfo uriInfo) {
        return uriInfo.getPath().startsWith("/travels");
    }
}
