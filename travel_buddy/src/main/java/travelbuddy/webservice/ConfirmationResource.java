/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.webservice;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.dao.IOrderBook;
import travelbuddy.dao.IProductCatalogue;
import travelbuddy.entity.Product;
import travelbuddy.entity.PurchaseOrder;

/**
 *
 * @author Ali
 */
@Path("/confirmation")
public class ConfirmationResource {
@EJB
 private IOrderBook orderBook;

    @GET
    @Path("id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPurchaseOrder(@PathParam("id") String id) {
        return Response.ok(new GenericEntity<PurchaseOrder>(orderBook.getPurchaseOrder(id)) {
        }).build();
    } 
}
