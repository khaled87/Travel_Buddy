package travelbuddy.proxy;

import com.google.gson.Gson;
import javax.ejb.Stateless;
<<<<<<< HEAD
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.ws.rs.core.Request;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
=======
>>>>>>> d9f857e3bb82cec3a821db9484981a3812f3f9b4
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;

@Stateless
public class QPXProxy implements IQPXProxy {

    private final String url = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=";
    private final String ApiKey = "AIzaSyBK9KNDV3W5tIc121fI07eRYDGcT3yGUJU";

    @Override
    public FlightResponse getFlightList(BasicQPXRequest fr) {
        String value = RequestHandler.execute(RequestHandler.RequestMethod.POST, url + ApiKey, fr.toParam());

        return new Gson().fromJson(value, FlightResponse.class);
    }
}
