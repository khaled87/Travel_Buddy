package travelbuddy.proxy;

import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;

@Local
public interface IQPXProxy {
    FlightResponse getFlightList(BasicQPXRequest qpxr);
}
