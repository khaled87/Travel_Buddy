package travelbuddy.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.City;
import travelbuddy.common.HotelRequest;
import travelbuddy.dao.IOrderBook;
import travelbuddy.dao.IProductCatalogue;
import travelbuddy.entity.Hotel;
import travelbuddy.entity.Product;
import travelbuddy.entity.Trip;
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
    
    @EJB
    private IOrderBook orderBook;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPackage(JsonObject jsonObject) {
        List<Trip> flightList = new ArrayList<>();
        Trip f1 = Trip.fromJson(jsonObject.getJsonObject("flight1"));
        Trip f2 = Trip.fromJson(jsonObject.getJsonObject("flight2"));
        if (f1 != null) {
            flightList.add(f1);
        }
        if (f2 != null) {
            flightList.add(f2);
        }
        Hotel nHotel = Hotel.fromJson(jsonObject.getJsonObject("hotel"));  
        Product p = getProduct(jsonObject.getJsonObject("product"), flightList, nHotel);

        productCatalogue.create(p);
        return Response.ok(new GenericEntity<Product>(p) {
        }).build();
    }

    private Product getProduct(JsonObject jProduct, List<Trip> trips, Hotel nHotel) {
        Product p = Product.fromJson(jProduct);
        p.setTrips(trips);
        p.setHotel(nHotel);
        return p;
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
        r = Response.ok(new GenericEntity<Collection<Hotel>>(eanProxy.getHotels(hr).getHotelList()) {
        }).build();
        return r;
    }
    
    @GET
    @Path("candelete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response canDelete(@PathParam("id") final long id) {
        JsonObject result = Json.createObjectBuilder().add("value", !orderBook.anyOrderWithProductId(id)).build();
        return Response.ok(new GenericEntity<JsonObject>(result) {}).build();
    }
}
