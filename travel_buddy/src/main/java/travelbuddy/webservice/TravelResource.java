/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.webservice;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;
import travelbuddy.dao.IProductCatalogue;
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
    
    @EJB
    private IProductCatalogue productCatalogue;

//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getFlightList(JsonObject jsonObject, JsonObject useless) {
//        BasicQPXRequest fr = new BasicQPXRequest();
//        fr.setOrigin(jsonObject.getString("origin"));
//        fr.setDestination(jsonObject.getString("destination"));
//        fr.setAdultCount(jsonObject.getInt("adultCount"));
//        //fr.setDate(jsonObject.getString("date"));
//        fr.setDate("2014-12-12");
//        fr.setSolution("3");
//        fr.setPreferredCabin("FIRST");
//        fr.setMaxPrice("SEK2000.0");
//        qpxProxy.getFlightList(fr);
//        return Response.ok(new GenericEntity<FlightResponse>(qpxProxy.getFlightList(fr)) {
//        }).build();
//            //return Response.ok(new GenericEntity<Product>(new Product("a", 3, null, null)) {}).build();
////        JsonObject count = Json.createObjectBuilder().add("value", 1).build();
////        return Response.ok(new GenericEntity<JsonObject>(count) {
////        }).build();
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPackage(JsonObject jsonObject) {
        Product p = new Product(jsonObject.getString("name"), (long) jsonObject.getInt("price"),jsonObject.getString("description"), null, null);
        productCatalogue.create(p);
        return Response.ok(new GenericEntity<Product>(p) {
        }).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") final long id) {
        productCatalogue.delete(id);
        return Response.ok().build();
    }
}
