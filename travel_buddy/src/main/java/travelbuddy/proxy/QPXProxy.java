package travelbuddy.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.entity.Flight;

@Stateless
public class QPXProxy implements IQPXProxy {

    public List<Flight> getFlightList(BasicQPXRequest qpxr) {
        return new ArrayList<>();
    }

    @Override
    public String getFlights(BasicQPXRequest qpxr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
