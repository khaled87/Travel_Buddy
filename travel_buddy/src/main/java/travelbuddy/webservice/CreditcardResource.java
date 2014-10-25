package travelbuddy.webservice;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import travelbuddy.common.BankResponse;
import travelbuddy.common.PaymentInfo;
import travelbuddy.proxy.IBankProxy;
 
@Path("/creditcard")
public class CreditcardResource {
    
    @EJB
    private IBankProxy bankProxy;

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response verify(JsonObject jsonObject) {
        PaymentInfo pi = new PaymentInfo(jsonObject.getString("account"), 
                                        (long)jsonObject.getInt("price"),
                                        jsonObject.getString("ccv"),
                                        jsonObject.getString("holder") );
        BankResponse br = bankProxy.verify(pi);
        return Response.ok(new GenericEntity<BankResponse>(br) {
        }).build();
    }
}
