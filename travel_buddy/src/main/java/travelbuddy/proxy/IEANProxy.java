package travelbuddy.proxy;

import javax.ejb.Local;
import travelbuddy.common.HotelRequest;
import travelbuddy.common.HotelResponse;

/**
* The EAN Proxy is the communicator between TravelBuddy and the hotel booking API: Expedia Affiliate Network.
* http://developer.ean.com/
*/
@Local
public interface IEANProxy {
    HotelResponse getHotels(HotelRequest hr);
}
