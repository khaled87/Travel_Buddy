/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;
import travelbuddy.common.Trip;
import travelbuddy.entity.Product;
import travelbuddy.proxy.IQPXProxy;

/**
 *
 * @author Ali
 */
@Path("/travels")
public class TravelResource {

    @EJB
    private IQPXProxy qpxProxy;

    @GET
    public Response shit(){
        return Response.ok().build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFlightList(JsonObject jsonObject) throws ParseException {
        BasicQPXRequest fr = new BasicQPXRequest();
        fr.setOrigin(jsonObject.getString("origin"));
        fr.setDestination(jsonObject.getString("destination"));
        fr.setAdultCount(jsonObject.getInt("adultCount"));
        fr.setDate(jsonObject.getString("date"));
        fr.setSolution("2");
        Response r = Response.ok(new GenericEntity<Collection<Trip>>(qpxProxy.getFlightList(fr)) {
        }).build();
        return r;
    }
}
