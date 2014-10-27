package travelbuddy.proxy;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import travelbuddy.common.HotelRequest;
import travelbuddy.common.HotelResponse;

/**
* The EAN Proxy is the communicator between TravelBuddy and the hotel booking API: Expedia Affiliate Network.
* http://developer.ean.com/
*/
@Stateless
public class EANProxy implements IEANProxy {
    private final String url = "http://dev.api.ean.com/ean-services/rs/hotel/v3/list?";
    
    @Override
    public HotelResponse getHotels(HotelRequest hr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.GET, url + hr.toRESTParamString());
        return new Gson().fromJson(value, HotelResponse.class);
    }
}
