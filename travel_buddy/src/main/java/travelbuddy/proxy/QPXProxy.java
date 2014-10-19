package travelbuddy.proxy;

import com.eclipsesource.json.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.ws.rs.core.Request;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightResponse;
import travelbuddy.entity.Flight;

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
