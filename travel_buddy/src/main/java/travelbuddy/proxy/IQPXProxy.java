package travelbuddy.proxy;

import java.util.Collection;
import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;
import travelbuddy.common.Trip;

@Local
public interface IQPXProxy {
    Collection<Trip>getFlightList(BasicQPXRequest qpxr);

  // String parseToFlightInfo();
}
