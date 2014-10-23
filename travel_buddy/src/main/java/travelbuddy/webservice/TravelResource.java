/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.Trip;
import travelbuddy.common.HotelRequest;
import travelbuddy.common.HotelResponse;
import travelbuddy.dao.IProductCatalogue;
import travelbuddy.entity.Product;
import travelbuddy.proxy.IEANProxy;
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
    private IEANProxy eanProxy;

    @EJB
    private IProductCatalogue productCatalogue;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPackage(JsonObject jsonObject) {
        Product p = new Product(jsonObject.getString("name"), (long) jsonObject.getInt("price"), jsonObject.getString("description"), null, null);
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

    @POST
    @Path("/hotels")
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

    public Response getHotelList(JsonObject jsonObject) {
        HotelRequest hr = new HotelRequest();
        try {
            hr.setCity(jsonObject.getString("city"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hr.setArrivalDate(sdf.parse(jsonObject.getString("arrivalDate")));
            hr.setDepartureDate(sdf.parse(jsonObject.getString("departureDate")));

        } catch (ParseException ex) {
            Logger.getLogger(TravelResource.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        Response r = Response.ok(new GenericEntity<HotelResponse>(eanProxy.getHotels(hr)) {
        }).build();
        return r;
    }
}
