package travelbuddy.proxy;

import javax.ejb.Local;
import travelbuddy.common.HotelRequest;

@Local
public interface IEANProxy {
    String getHotels(HotelRequest hr);
}
