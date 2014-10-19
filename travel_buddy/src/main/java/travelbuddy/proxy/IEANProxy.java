package travelbuddy.proxy;

import javax.ejb.Local;
import travelbuddy.common.HotelRequest;
import travelbuddy.common.HotelResponse;

@Local
public interface IEANProxy {
    HotelResponse getHotels(HotelRequest hr);
}
