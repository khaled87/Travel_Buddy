package travelbuddy.proxy;

import java.util.Collection;
import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.entity.Trip;

/**
* The QPX Proxy is the communicator between TravelBuddy and the flight booking API: QPX Express API.
* https://developers.google.com/qpx-express/
*/
@Local
public interface IQPXProxy {
    Collection<Trip>getFlightList(BasicQPXRequest qpxr);

  // String parseToFlightInfo();
}
