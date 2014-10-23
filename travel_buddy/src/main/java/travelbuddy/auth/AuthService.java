/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.auth;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tin
 */
@Path("/auth")
public class AuthService {

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(String s) {
        if (AuthFilter.isAuthorized(s)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
