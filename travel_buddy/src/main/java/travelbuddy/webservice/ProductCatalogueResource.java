package travelbuddy.webservice;

import travelbuddy.dao.IProductCatalogue;
import travelbuddy.entity.Product;
import java.util.Collection;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductCatalogueResource {

    @EJB
    private IProductCatalogue productCatalogue;

    @GET
    @Path("range")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(@QueryParam("fst") int fst, @QueryParam("count") int count) {
        return Response.ok(new GenericEntity<Collection<Product>>(productCatalogue.findRange(fst, count)) {
        }).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {
        return Response.ok(new GenericEntity<Product>(productCatalogue.find(id)) {
        }).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public Response count() {
        JsonObject count = Json.createObjectBuilder().add("value", productCatalogue.count()).build();
        return Response.ok(new GenericEntity<JsonObject>(count) {
        }).build();
    }
}
