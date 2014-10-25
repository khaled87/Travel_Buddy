package travelbuddy.proxy;

import java.util.Collection;
import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.TripObj;

@Local
public interface IQPXProxy {
    Collection<TripObj>getFlightList(BasicQPXRequest qpxr);

  // String parseToFlightInfo();
}
