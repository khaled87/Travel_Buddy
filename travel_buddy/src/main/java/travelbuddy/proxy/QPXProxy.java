package travelbuddy.proxy;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.entity.Flight;

@Stateless
public class QPXProxy implements IQPXProxy {

    @EJB
    private QPXProxy qPXProxy;
    private final String url = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=";
    private final String ApiKey = "AIzaSyBK9KNDV3W5tIc121fI07eRYDGcT3yGUJU";

    @Override
    public String getFlightList(BasicQPXRequest fr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.POST, url + ApiKey, fr.toParam());
        // TODO: convert string value to hotel list
        return value;
    }

    //If the above dont work, then the below will be implemented
   /* @Override
    public List<Flight> getFlights(BasicQPXRequest qpxr) {
        return new ArrayList<>();
    }*/

    //The code below is used for testing!
    /* public static void main(String[]args){
     BasicQPXRequest qpx = new BasicQPXRequest();
     QPXProxy prox = new QPXProxy(); 
     qpx.setAdultCount(1);
     //qpx.setChildCount(1);
     //  qpx.setInfantInSeatCount(1);
     qpx.setDestination("GOT");
     qpx.setOrigin("STO");
     qpx.setDate("2014-11-30");
     qpx.setSolution("1");
     qpx.setPreferredCabin("FIRST");
     qpx.setMaxPrice("SEK2000.0");
     prox.getFlightList(qpx);
     }*/
}

