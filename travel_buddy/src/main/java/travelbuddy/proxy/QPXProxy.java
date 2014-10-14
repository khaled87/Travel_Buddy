package travelbuddy.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.entity.Flight;

@Stateless
public class QPXProxy implements IQPXProxy {

    @Override
    public List<Flight> getFlights(BasicQPXRequest qpxr) {
        return new ArrayList<>();
    }
    
}
