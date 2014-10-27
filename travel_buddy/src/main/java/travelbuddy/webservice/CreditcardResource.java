package travelbuddy.webservice;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BankResponse;
import travelbuddy.common.PaymentInfo;
import travelbuddy.dao.IOrderBook;
import travelbuddy.entity.*;
import travelbuddy.proxy.IBankProxy;

/**
* The credit card resource is used in the payment process.
*/
@Path("/creditcard")
public class CreditcardResource {
    
    @EJB
    private IBankProxy bankProxy;
    
    @EJB
    private IOrderBook orderBook;

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response verify(JsonObject jsonObject) {
        PurchaseOrder po = new PurchaseOrder();
        Product p = getProduct(jsonObject.getJsonObject("product"));
        po.setUserFullName(jsonObject.getString("holder"));
        po.setProduct(p);
        po.setUserEmail(jsonObject.getString("email"));
        
        PaymentInfo pi = new PaymentInfo(
                jsonObject.getString("account"),
                p.getPrice(),
                jsonObject.getString("ccv"),
                jsonObject.getString("holder") );
        BankResponse br = bankProxy.verify(pi);
        
        if (br.getOk().equals("okay")) {
           br.setConfirmationCode(orderBook.createPurchaseOrder(po));
        }
        
        
        return Response.ok(new GenericEntity<BankResponse>(br) {
        }).build();
    }

    private Product getProduct(JsonObject jsonObject) {
        Product p = new Product();
        
        p.setId((long) jsonObject.getInt("id"));
        p.setName(jsonObject.getString("name"));
        p.setPrice(jsonObject.getInt("price"));
        return p;
    }
}
