package travelbuddy.proxy;

import java.util.List;
import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.entity.Flight;

@Local
public interface IQPXProxy {
    List<Flight> getFlights(BasicQPXRequest qpxr);
}
