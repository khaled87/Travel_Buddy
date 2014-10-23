/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.auth;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Tin
 */
@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Context
    SecurityContext sCtx;

    @Override
    public void filter(ContainerRequestContext rCtx) throws WebApplicationException {
        UriInfo uriInfo = rCtx.getUriInfo();

        if (uriInfo.getPath().startsWith("/product")) {
            boolean isAuthorized = false;
            String auth = rCtx.getHeaderString("authorization");
            if (auth != null && isAuthorized(auth)) {
                isAuthorized = true;
            }

            if (!isAuthorized) {
                rCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("No authorization").build());
            }
        }
    }

    public static boolean isAuthorized(String auth) {
        byte[] base64 = DatatypeConverter.parseBase64Binary(auth.split(" ")[1].trim());
        String credentials = new String(base64);

        return "qqq:111".equals(credentials);
    }
}
