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
        //String value = RequestHandler.execute(RequestHandler.RequestMethod.POST, url + ApiKey, fr.toParam());
        // System.out.println("TITTA HÄÄÄÄR" + value);
        String value = "{\n" +
" \"kind\": \"qpxExpress#tripsSearch\",\n" +
" \"trips\": {\n" +
"  \"kind\": \"qpxexpress#tripOptions\",\n" +
"  \"requestId\": \"fEcfwUW5fyBySOeWI0Kud6\",\n" +
"  \"data\": {\n" +
"   \"kind\": \"qpxexpress#data\",\n" +
"   \"airport\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"ARN\",\n" +
"     \"city\": \"STO\",\n" +
"     \"name\": \"Stockholm Arlanda\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"BKK\",\n" +
"     \"city\": \"BKK\",\n" +
"     \"name\": \"Bangkok Suvarnabhumi International\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"IST\",\n" +
"     \"city\": \"IST\",\n" +
"     \"name\": \"Istanbul Ataturk\"\n" +
"    }\n" +
"   ],\n" +
"   \"city\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"BKK\",\n" +
"     \"name\": \"Bangkok\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"IST\",\n" +
"     \"name\": \"Istanbul\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"STO\",\n" +
"     \"name\": \"Stockholm\"\n" +
"    }\n" +
"   ],\n" +
"   \"aircraft\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"321\",\n" +
"     \"name\": \"Airbus A321\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"330\",\n" +
"     \"name\": \"Airbus A330\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"343\",\n" +
"     \"name\": \"Airbus A340\"\n" +
"    }\n" +
"   ],\n" +
"   \"tax\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"YA_1\",\n" +
"     \"name\": \"Swedish Passenger Charge\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"TR_001\",\n" +
"     \"name\": \"Turkish Int'l Airport Service Charge\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"YR\",\n" +
"     \"name\": \"TK YR surcharge\"\n" +
"    }\n" +
"   ],\n" +
"   \"carrier\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#carrierData\",\n" +
"     \"code\": \"TK\",\n" +
"     \"name\": \"Turkish Airlines Inc.\"\n" +
"    }\n" +
"   ]\n" +
"  },\n" +
"  \"tripOption\": [\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK3960\",\n" +
"    \"id\": \"Zxh0HTeQfm3O1F1UsKc7uF002\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 935,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 205,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"1794\"\n" +
"        },\n" +
"        \"id\": \"GfZqJr8x6FsYKs5S\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LX5PlqBYDfQb6oJ8\",\n" +
"          \"aircraft\": \"321\",\n" +
"          \"arrivalTime\": \"2014-10-31T16:35+02:00\",\n" +
"          \"departureTime\": \"2014-10-31T12:10+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"IST\",\n" +
"          \"originTerminal\": \"5\",\n" +
"          \"destinationTerminal\": \"I\",\n" +
"          \"duration\": 205,\n" +
"          \"mileage\": 1372,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 195\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 535,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"64\"\n" +
"        },\n" +
"        \"id\": \"G3dNvd4wAb2hIfW8\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LhzzpIhMFSmkTjEc\",\n" +
"          \"aircraft\": \"330\",\n" +
"          \"arrivalTime\": \"2014-11-01T09:45+07:00\",\n" +
"          \"departureTime\": \"2014-10-31T19:50+02:00\",\n" +
"          \"origin\": \"IST\",\n" +
"          \"destination\": \"BKK\",\n" +
"          \"originTerminal\": \"I\",\n" +
"          \"duration\": 535,\n" +
"          \"mileage\": 4662,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ]\n" +
"     }\n" +
"    ],\n" +
"    \"pricing\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#pricingInfo\",\n" +
"      \"fare\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#fareInfo\",\n" +
"        \"id\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"carrier\": \"TK\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"BKK\",\n" +
"        \"basisCode\": \"EN2PXOW\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"segmentId\": \"GfZqJr8x6FsYKs5S\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"segmentId\": \"G3dNvd4wAb2hIfW8\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK2290\",\n" +
"      \"saleFareTotal\": \"SEK2290\",\n" +
"      \"saleTaxTotal\": \"SEK1670\",\n" +
"      \"saleTotal\": \"SEK3960\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 1\n" +
"      },\n" +
"      \"tax\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YR\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YR\",\n" +
"        \"salePrice\": \"SEK1464\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"TR_001\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"TR\",\n" +
"        \"country\": \"TR\",\n" +
"        \"salePrice\": \"SEK46\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YA_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"YA\",\n" +
"        \"country\": \"SE\",\n" +
"        \"salePrice\": \"SEK160\"\n" +
"       }\n" +
"      ],\n" +
"      \"fareCalculation\": \"STO TK X/IST TK BKK 323.32EN2PXOW NUC 323.32 END ROE 7.08276 FARE SEK 2290 XT 160YA 46TR 1464YR\",\n" +
"      \"latestTicketingTime\": \"2014-10-29T23:59-04:00\",\n" +
"      \"ptc\": \"ADT\",\n" +
"      \"refundable\": true\n" +
"     }\n" +
"    ]\n" +
"   },\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK3960\",\n" +
"    \"id\": \"Zxh0HTeQfm3O1F1UsKc7uF001\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 930,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 205,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"1796\"\n" +
"        },\n" +
"        \"id\": \"Gcj0f2HzieyIpsAs\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LzK0PIl1wYJKfKA8\",\n" +
"          \"aircraft\": \"321\",\n" +
"          \"arrivalTime\": \"2014-10-31T21:45+02:00\",\n" +
"          \"departureTime\": \"2014-10-31T17:20+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"IST\",\n" +
"          \"originTerminal\": \"5\",\n" +
"          \"destinationTerminal\": \"I\",\n" +
"          \"duration\": 205,\n" +
"          \"mileage\": 1372,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 190\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 535,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"68\"\n" +
"        },\n" +
"        \"id\": \"GoUINXslGXo-uQ8A\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LEPJS90zEb-El-Xz\",\n" +
"          \"aircraft\": \"343\",\n" +
"          \"arrivalTime\": \"2014-11-01T14:50+07:00\",\n" +
"          \"departureTime\": \"2014-11-01T00:55+02:00\",\n" +
"          \"origin\": \"IST\",\n" +
"          \"destination\": \"BKK\",\n" +
"          \"originTerminal\": \"I\",\n" +
"          \"duration\": 535,\n" +
"          \"mileage\": 4662,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ]\n" +
"     }\n" +
"    ],\n" +
"    \"pricing\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#pricingInfo\",\n" +
"      \"fare\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#fareInfo\",\n" +
"        \"id\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"carrier\": \"TK\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"BKK\",\n" +
"        \"basisCode\": \"EN2PXOW\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"segmentId\": \"GoUINXslGXo-uQ8A\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"A0x02jrP/YCNtGvXkLLt6LCTFp5V+OStirmTznzK/yNU\",\n" +
"        \"segmentId\": \"Gcj0f2HzieyIpsAs\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK2290\",\n" +
"      \"saleFareTotal\": \"SEK2290\",\n" +
"      \"saleTaxTotal\": \"SEK1670\",\n" +
"      \"saleTotal\": \"SEK3960\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 1\n" +
"      },\n" +
"      \"tax\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YR\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YR\",\n" +
"        \"salePrice\": \"SEK1464\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"TR_001\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"TR\",\n" +
"        \"country\": \"TR\",\n" +
"        \"salePrice\": \"SEK46\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YA_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"YA\",\n" +
"        \"country\": \"SE\",\n" +
"        \"salePrice\": \"SEK160\"\n" +
"       }\n" +
"      ],\n" +
"      \"fareCalculation\": \"STO TK X/IST TK BKK 323.32EN2PXOW NUC 323.32 END ROE 7.08276 FARE SEK 2290 XT 160YA 46TR 1464YR\",\n" +
"      \"latestTicketingTime\": \"2014-10-29T23:59-04:00\",\n" +
"      \"ptc\": \"ADT\",\n" +
"      \"refundable\": true\n" +
"     }\n" +
"    ]\n" +
"   }\n" +
"  ]\n" +
" }\n" +
"}";

        return parseToFlightInfo(value);

        // return new Gson().fromJson(value, FlightResponse.class);
    }

    public Collection<Trip> parseToFlightInfo(String httpResponse) {
        clearPreviousData();
        JsonParser parser;
        parser = Json.createParser(new StringReader(httpResponse));

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
                        prevSaleTotal = saleTotal;
                    }

                    saleTotal = parser.getString();
                }
            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("slice")) {
                count++;
                kiloFound = false;

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

                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("destination")) {
                        parser.next();
                        destination = parser.getString();

                    } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("meal")) {
                        parser.next();
                        meal = parser.getString();

                    } else if (event.equals(JsonParser.Event.END_OBJECT)) {
                        createSubTrip(cabin, arrivalTime, departureTime, origin, destination, meal);
                    }

                }

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("freeBaggageOption")) {

                parser.next();

                while (!event.equals(JsonParser.Event.END_ARRAY)) {
                    event = parser.next();

                    if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("kilos")) {
                        System.out.println("we got kilooooooos");
                        kiloFound = true;

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
                if (kiloFound == false) {
                    System.out.println("2");
                    maxFreeBaggage = "Not specified";
                    prevMaxFreeBaggage = "Not specified";
                }

            } else if (event.equals(JsonParser.Event.KEY_NAME) && parser.getString().matches("airport")) {
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

        System.out.println(
                "+++At the end+++++" + saleTotal + " " + maxFreeBaggage);
        createTrip(saleTotal, prevMaxFreeBaggage);
        saleTotal = null;

        printAll(tripList);
        return tripList;
    }

    private void createTrip(String saleTotal, String prevMaxFreeBaggage) {
        System.out.println("the sales total in create trip is " + saleTotal);
        Trip trip = new Trip();
        trip.setSaleTotal(saleTotal);
        trip.setMaxFreeBaggage(prevMaxFreeBaggage);
        System.out.println("SKITSKALLE " + subtrip.get(0).getArrivalTime());
        trip.setSubtripList(subtrip);
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
            for (int j = 0; j < tripList.get(i).getSubtripList().size(); j++) {
                System.out.println("-------subTrip--------------- " + " " + j);
                System.out.println("subTrip size " + subtrip.size());

                System.out.println("departureTIme" + tripList.get(i).getSubtripList().get(j).getCabin());
                System.out.println("arrivalTime" + tripList.get(i).getSubtripList().get(j).getArrivalTime());
                System.out.println("departureTIme" + tripList.get(i).getSubtripList().get(j).getDepartureTime());
                System.out.println("origin" + tripList.get(i).getSubtripList().get(j).getOrigin());
                System.out.println("destination" + tripList.get(i).getSubtripList().get(j).getDestination());
                System.out.println("meal" + tripList.get(i).getSubtripList().get(j).getMeal());
            }
        }
    }

    private void clearPreviousData() {
        tripList.clear();
        saleTotal = null;
        prevSaleTotal = null;
        prevMaxFreeBaggage = null;
        maxFreeBaggage = null;
    }

}
