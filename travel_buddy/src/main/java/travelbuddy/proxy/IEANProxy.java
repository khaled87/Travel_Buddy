package travelbuddy.proxy;

import java.util.List;
import javax.ejb.Local;
import travelbuddy.common.HotelRequest;
import travelbuddy.entity.Hotel;

@Local
public interface IEANProxy {
    List<Hotel> getHotels(HotelRequest hr);
}
