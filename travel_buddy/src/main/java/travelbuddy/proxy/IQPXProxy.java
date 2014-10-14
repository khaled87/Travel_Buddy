package travelbuddy.proxy;

import javax.ejb.Local;
import travelbuddy.common.BasicQPXRequest;

@Local
public interface IQPXProxy {
    String getFlights(BasicQPXRequest qpxr);
}
