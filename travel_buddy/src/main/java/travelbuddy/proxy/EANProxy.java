package travelbuddy.proxy;

import javax.ejb.Stateless;
import travelbuddy.common.HotelRequest;

@Stateless
public class EANProxy implements IEANProxy {
    private final String url = "http://dev.api.ean.com/ean-services/rs/hotel/v3/list?";
    
    public String getHotelList(HotelRequest hr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.GET, url + hr.toRESTParamString());
        // TODO: convert string value to hotel list
        return value;
    }
}
