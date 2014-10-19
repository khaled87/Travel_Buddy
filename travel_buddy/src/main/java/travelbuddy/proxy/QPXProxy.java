package travelbuddy.proxy;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;

@Stateless
public class QPXProxy implements IQPXProxy {

    private QPXProxy qPXProxy;
    private final String url = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=";
    private final String ApiKey = "AIzaSyBK9KNDV3W5tIc121fI07eRYDGcT3yGUJU";

    @Override
    public FlightResponse getFlightList(BasicQPXRequest fr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.POST, url + ApiKey, fr.toParam());

        return new Gson().fromJson(value, FlightResponse.class);

    }
}
