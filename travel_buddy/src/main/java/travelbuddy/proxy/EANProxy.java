package travelbuddy.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import travelbuddy.common.HotelRequest;
import travelbuddy.entity.Hotel;

@Stateless
public class EANProxy implements IEANProxy {
    private final String url = "http://dev.api.ean.com/ean-services/rs/hotel/v3/list?";
    
    @Override
    public String getHotels(HotelRequest hr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.GET, url + hr.toRESTParamString());
        // TODO: convert string value to hotel list
        return value;
    }

    public List<Hotel> getHotelList(HotelRequest hr) {
        return new ArrayList<>();
    }
}
