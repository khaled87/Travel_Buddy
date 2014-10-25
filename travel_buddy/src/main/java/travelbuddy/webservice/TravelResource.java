/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.City;
import travelbuddy.common.Trip;
import travelbuddy.common.HotelRequest;
import travelbuddy.dao.IProductCatalogue;
import travelbuddy.entity.Hotel;
import travelbuddy.entity.Product;
import travelbuddy.proxy.IEANProxy;
import travelbuddy.proxy.IQPXProxy;


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
        JsonObject jProduct = jsonObject.getJsonObject("product");
        JsonObject jHotel = jsonObject.getJsonObject("hotel");
        List<Trip> flightList = new ArrayList<>();
        flightList.add((Trip) jsonObject.getJsonObject("flight1"));
        flightList.add((Trip) jsonObject.getJsonObject("flight2"));
        Hotel nHotel = new Hotel();
        nHotel.setName(jHotel.getString("name"));
        nHotel.setAddress1(jHotel.getString("address1"));
        nHotel.setPrice((long)jHotel.getInt("price"));  
<<<<<<< HEAD
        Product p = new Product(
                jProduct.getString("name"), 
                (long) jProduct.getInt("price"),
                jProduct.getString("description"), 
                null, nHotel, 
                jProduct.getString("imgSrc"));
=======
        
        Product p = new Product( jProduct.getString("name"), (long) jProduct.getInt("price"),jProduct.getString("description"), null, nHotel);
>>>>>>> 355218538df796b156c5015e559c62ea44dfb851
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
    @Path("/flights")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})

    public Response getFlightList(JsonObject jsonObject) throws ParseException {
        City city = new City();
        city.createAbbrevList();
        String origin = city.getCityAbbrev(jsonObject.getString("origin"));
        String destination = city.getCityAbbrev(jsonObject.getString("destination"));
        BasicQPXRequest fr = new BasicQPXRequest();
        fr.setOrigin(origin);
        fr.setDestination(destination);
        fr.setAdultCount(jsonObject.getInt("adultCount"));
        fr.setDate(jsonObject.getString("date"));
        fr.setSolution("2");
        Response r = Response.ok(new GenericEntity<Collection<Trip>>(qpxProxy.getFlightList(fr)) {
        }).build();
        return r;
    }

    @POST
    @Path("/hotels")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHotelList(JsonObject jsonObject) {
        HotelRequest hr = new HotelRequest();
        try {
            hr.setCity(jsonObject.getString("city"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hr.setArrivalDate(sdf.parse(jsonObject.getString("arrivalDate")));
            hr.setDepartureDate(sdf.parse(jsonObject.getString("departureDate")));
            hr.setNumberOfResults(3);
            hr.setCurrencyCode("SEK");
        } catch (ParseException ex) {
            Logger.getLogger(TravelResource.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Response r;
        r = Response.ok(new GenericEntity<Collection<Hotel>>( eanProxy.getHotels(hr).getHotelList()) {
        }).build();
        return r;
    }
}
