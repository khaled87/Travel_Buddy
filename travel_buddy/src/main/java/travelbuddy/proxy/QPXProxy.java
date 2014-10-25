package travelbuddy.proxy;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import travelbuddy.common.BasicQPXRequest;
import travelbuddy.common.FlightInfo;
import travelbuddy.entity.SubTrip;
import travelbuddy.entity.Trip;

@Stateless
public class QPXProxy implements IQPXProxy {

    private final String url = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=";
    private final String ApiKey = "AIzaSyC3OAnGCXMYwYHyTjB-9_wIhxCC31eiV7E";
    private List<FlightInfo> FlightInfoList = new ArrayList<>();
    //private List<Slice>slices = new ArrayList<>();
    //private List<Leg>leg = new ArrayList<>();
    //private final Slice slice = new Slice();
    private final List<SubTrip> subtrip = new ArrayList<>();
    private final List<Trip> tripList = new ArrayList<>();
    private final List<String> airPortList = new ArrayList<>();
    private String airPortData;
    int count;
    private String arrivalTime;
    private String departureTime;
    private String origin;
    private String destination;
    private String meal;
    private String saleTotal;
    private String prevSaleTotal;
    private String cabin;
    private String maxFreeBaggage;
    private String prevMaxFreeBaggage;
    private boolean kiloFound;
    // SubTrip sub = new SubTrip();

    @Override
    public Collection<Trip> getFlightList(BasicQPXRequest fr) {
       String value = RequestHandler.execute(RequestHandler.RequestMethod.POST, url + ApiKey, fr.toParam());
       // System.out.println("TITTA HÄÄÄÄR" + value);
      
        
        
       return parseToFlightInfo(value);
       // return new Gson().fromJson(value, FlightResponse.class);
    }

    public Collection<Trip> parseToFlightInfo(String thing) {
        String duration = null;
        JsonParser parser;
        parser = Json.createParser(new StringReader(thing));

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            while (parser.hasNext() && !(event.equals(JsonParser.Event.KEY_NAME))) {
                event = parser.next();

            }
            if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("kind")) {
                parser.next();
                if (parser.getString().matches("qpxexpress#tripOption")) {
                    while (!parser.getString().matches("saleTotal")) {
                        parser.next();
                    }
                    parser.next();
                    if (saleTotal != null) {
                        System.out.println("its null");
                        prevSaleTotal = saleTotal;
                    }

                    saleTotal = parser.getString();
                }

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("slice")) {
                count++;
                kiloFound = false;
                System.out.println("we got slice" + count);
                System.out.println(" sales total in slice (Correct) " + saleTotal);

                for (int i = 0; i < subtrip.size(); i++) {
                    System.out.println("subTtrip in slice" + i + subtrip.get(i).getArrivalTime());
                }

                if (subtrip.size() > 0) {
                    System.out.println("subTrip Size " + subtrip.size());
                    createTrip(prevSaleTotal, prevMaxFreeBaggage);
                    System.out.println("subTrip Size afer clear " + subtrip.size());

                }

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("cabin")) {
                parser.next();
                cabin = parser.getString();
                // System.out.println("cabin " + parser.getString());
            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("leg")) {
                System.out.println("leg found**");
                meal = "Not Specified";
                parser.next();

                while (!event.equals(JsonParser.Event.END_ARRAY)) {
                    event = parser.next();

                    if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("arrivalTime")) {
                        parser.next();
                        arrivalTime = parser.getString();
                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("departureTime")) {
                        parser.next();
                        departureTime = parser.getString();
                        //createSubTrip(arrivalTime, departureTime);
                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("origin")) {
                        parser.next();
                        origin = parser.getString();

                        //createSubTrip(arrivalTime, departureTime);
                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("destination")) {
                        parser.next();
                        destination = parser.getString();
                        //createSubTrip(arrivalTime, departureTime);
                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("meal")) {
                        parser.next();
                        meal = parser.getString();

                    }

                }
                createSubTrip(cabin, arrivalTime, departureTime, origin, destination, meal);

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("freeBaggageOption")) {

                parser.next();

                while (!event.equals(JsonParser.Event.END_ARRAY)) {
                    event = parser.next();

                    if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("kilos")) {
                        System.out.println("we got kilooooooos");
                        kiloFound = true;

                        // parser.next();
                        if (maxFreeBaggage != null) {
                            System.out.println("???????????maxfreebagage???????+" + maxFreeBaggage);
                            prevMaxFreeBaggage = maxFreeBaggage;
                            System.out.println("???????????previous is???????+" + prevMaxFreeBaggage);
                            parser.next();
                            maxFreeBaggage = parser.getString();
                            System.out.println("???????????maxfreebagage???????+" + maxFreeBaggage);

                        } else {
                            System.out.println("bortaaaa" + prevMaxFreeBaggage + maxFreeBaggage);
                            parser.next();
                           // maxFreeBaggage = parser.getString();
                            prevMaxFreeBaggage = parser.getString();
                            System.out.println("borde ej vara null" + prevMaxFreeBaggage + maxFreeBaggage);

                        }

                    }

                }
                System.out.println("1");
                if (kiloFound == false) {
                    System.out.println("2");
                    maxFreeBaggage = "Not specified";
                    prevMaxFreeBaggage = "Not specified";
                }

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("airport")) {
                System.out.println("Airport**");
                parser.next();

                while (!event.equals(JsonParser.Event.END_ARRAY)) {
                    event = parser.next();
                    if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("code")) {
                        parser.next();
                        airPortData = parser.getString();
                        System.out.println("code is " + airPortData);
                    }
                    if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("name")) {
                        parser.next();
                        System.out.println("we got a name" + parser.getString());
                        airPortData = airPortData.concat(",");
                        airPortData = airPortData.concat(parser.getString());
                        airPortList.add(airPortData);
                        System.out.println("airPortData contains " + airPortData);
                    }

                }
            }
        }

        System.out.println("+++At the end+++++" + saleTotal + " " + maxFreeBaggage);
        createTrip(saleTotal, prevMaxFreeBaggage);

        printAll(tripList);
        return tripList;
    }

    private void createTrip(String saleTotal, String prevMaxFreeBaggage) {
        System.out.println("the sales total in create trip is " + saleTotal);
        Trip trip = new Trip();
        trip.setSaleTotal(saleTotal);
        trip.setMaxFreeBaggage(prevMaxFreeBaggage);
        trip.setSubTrips(subtrip);
        tripList.add(trip);
        subtrip.clear();

    }

    private void createSubTrip(String cabin, String arrivalTime, String departureTime, String origin, String destination, String meal) {

        for (int i = 0; i < airPortList.size(); i++) {
            if (airPortList.get(i).contains(origin)) {
                origin = airPortList.get(i);

            } else if (airPortList.get(i).contains(destination)) {
                destination = airPortList.get(i);
            }
        }
        SubTrip sub = new SubTrip();
        sub.setCabin(cabin);
        sub.setArrivalTime(arrivalTime);
        sub.setDepartureTime(departureTime);
        sub.setOrigin(origin);
        sub.setDestination(destination);
        sub.setMeal(meal);
        subtrip.add(sub);
    }

  //  below code is for test purposes only
     private void printAll(List<Trip> tripList) {
        
     for (int i = 0; i < tripList.size(); i++) {
     System.out.println("**********tripObject**********' " + " " + i);
     System.out.println("departureTIme" + tripList.get(i).getSaleTotal());
     System.out.println("Free KG" + tripList.get(i).getMaxFreeBaggage());
     for (int j = 0; j < tripList.get(i).getSubTrips().size(); j++) {
     System.out.println("-------subTrip--------------- " + " " + j);
     System.out.println("subTrip size " + subtrip.size());
                
     System.out.println("departureTIme" + tripList.get(i).getSubTrips().get(j).getCabin());
     System.out.println("arrivalTime" + tripList.get(i).getSubTrips().get(j).getArrivalTime());
     System.out.println("departureTIme" + tripList.get(i).getSubTrips().get(j).getDepartureTime());
     System.out.println("origin" + tripList.get(i).getSubTrips().get(j).getOrigin());
     System.out.println("destination" + tripList.get(i).getSubTrips().get(j).getDestination());
     System.out.println("meal" + tripList.get(i).getSubTrips().get(j).getMeal());
     }
     }
     }
}
