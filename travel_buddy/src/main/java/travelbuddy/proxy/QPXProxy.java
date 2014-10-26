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
"  \"requestId\": \"VNMq5TX8QTdJNhvuI0KuNH\",\n" +
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
"     \"code\": \"AUH\",\n" +
"     \"city\": \"AUH\",\n" +
"     \"name\": \"Abu Dhabi International\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"BKK\",\n" +
"     \"city\": \"BKK\",\n" +
"     \"name\": \"Bangkok Suvarnabhumi International\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"DUS\",\n" +
"     \"city\": \"DUS\",\n" +
"     \"name\": \"Duesseldorf International\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"IST\",\n" +
"     \"city\": \"IST\",\n" +
"     \"name\": \"Istanbul Ataturk\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"SGN\",\n" +
"     \"city\": \"SGN\",\n" +
"     \"name\": \"Ho Chi Minh City Tan Son Nhat International\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#airportData\",\n" +
"     \"code\": \"TXL\",\n" +
"     \"city\": \"BER\",\n" +
"     \"name\": \"Berlin Tegel\"\n" +
"    }\n" +
"   ],\n" +
"   \"city\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"AUH\",\n" +
"     \"name\": \"Abu Dhabi\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"BER\",\n" +
"     \"name\": \"Berlin\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"BKK\",\n" +
"     \"name\": \"Bangkok\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"DUS\",\n" +
"     \"name\": \"Dusseldorf\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"IST\",\n" +
"     \"name\": \"Istanbul\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#cityData\",\n" +
"     \"code\": \"SGN\",\n" +
"     \"name\": \"Ho Chi Minh City\"\n" +
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
"     \"code\": \"332\",\n" +
"     \"name\": \"Airbus A330\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"343\",\n" +
"     \"name\": \"Airbus A340\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"736\",\n" +
"     \"name\": \"Boeing 737\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#aircraftData\",\n" +
"     \"code\": \"738\",\n" +
"     \"name\": \"Boeing 737\"\n" +
"    }\n" +
"   ],\n" +
"   \"tax\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"DE_1\",\n" +
"     \"name\": \"German Airport Security Tax\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"YA_1\",\n" +
"     \"name\": \"Swedish Passenger Charge\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"RA_2\",\n" +
"     \"name\": \"German Passenger Service Charge\"\n" +
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
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#taxData\",\n" +
"     \"id\": \"YQ\",\n" +
"     \"name\": \"EY YQ surcharge\"\n" +
"    }\n" +
"   ],\n" +
"   \"carrier\": [\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#carrierData\",\n" +
"     \"code\": \"AB\",\n" +
"     \"name\": \"Air Berlin PLC & Co. Luftverkehrs KG\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#carrierData\",\n" +
"     \"code\": \"EY\",\n" +
"     \"name\": \"Etihad Airways\"\n" +
"    },\n" +
"    {\n" +
"     \"kind\": \"qpxexpress#carrierData\",\n" +
"     \"code\": \"SK\",\n" +
"     \"name\": \"Scandinavian Airlines System (SAS)\"\n" +
"    },\n" +
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
"    \"saleTotal\": \"SEK7572\",\n" +
"    \"id\": \"afJyHYtK0GJQmQI2hd2s9O004\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 1165,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 120,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"SK\",\n" +
"         \"number\": \"2625\"\n" +
"        },\n" +
"        \"id\": \"G5IYpuq8g-GSgSg0\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"U\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LziRe2yt89hNtwg9\",\n" +
"          \"aircraft\": \"736\",\n" +
"          \"arrivalTime\": \"2014-11-04T19:10+01:00\",\n" +
"          \"departureTime\": \"2014-11-04T17:10+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"DUS\",\n" +
"          \"originTerminal\": \"5\",\n" +
"          \"duration\": 120,\n" +
"          \"mileage\": 721,\n" +
"          \"meal\": \"Food and Beverages for Purchase\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 115\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 390,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"EY\",\n" +
"         \"number\": \"1998\"\n" +
"        },\n" +
"        \"id\": \"GUS57tlun3O5o4kt\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"V\",\n" +
"        \"bookingCodeCount\": 7,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LTh4qn9MmJl1GfrW\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T06:35+04:00\",\n" +
"          \"departureTime\": \"2014-11-04T21:05+01:00\",\n" +
"          \"origin\": \"DUS\",\n" +
"          \"destination\": \"AUH\",\n" +
"          \"destinationTerminal\": \"1\",\n" +
"          \"duration\": 390,\n" +
"          \"operatingDisclosure\": \"OPERATED BY AIR BERLIN PLC & CO. LUFTVERKEHRS KG\",\n" +
"          \"mileage\": 3121,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 115\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 425,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"EY\",\n" +
"         \"number\": \"441\"\n" +
"        },\n" +
"        \"id\": \"GnNxf0XrHhl0G7kp\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"V\",\n" +
"        \"bookingCodeCount\": 7,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LAZ5JmJsGqKdgdkW\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T18:35+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T08:30+04:00\",\n" +
"          \"origin\": \"AUH\",\n" +
"          \"destination\": \"SGN\",\n" +
"          \"originTerminal\": \"3\",\n" +
"          \"destinationTerminal\": \"2\",\n" +
"          \"duration\": 425,\n" +
"          \"mileage\": 3528,\n" +
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
"        \"id\": \"AN87QCKZLV3YbvaZoxuzKPfbAgLe4Fn06I5atfaxdrqc\",\n" +
"        \"carrier\": \"EY\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"SGN\",\n" +
"        \"basisCode\": \"VLAPOWSE\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AN87QCKZLV3YbvaZoxuzKPfbAgLe4Fn06I5atfaxdrqc\",\n" +
"        \"segmentId\": \"G5IYpuq8g-GSgSg0\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AN87QCKZLV3YbvaZoxuzKPfbAgLe4Fn06I5atfaxdrqc\",\n" +
"        \"segmentId\": \"GnNxf0XrHhl0G7kp\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AN87QCKZLV3YbvaZoxuzKPfbAgLe4Fn06I5atfaxdrqc\",\n" +
"        \"segmentId\": \"GUS57tlun3O5o4kt\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK1859\",\n" +
"      \"saleFareTotal\": \"SEK1859\",\n" +
"      \"saleTaxTotal\": \"SEK1927\",\n" +
"      \"saleTotal\": \"SEK3786\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 2\n" +
"      },\n" +
"      \"tax\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"RA_2\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"RA\",\n" +
"        \"country\": \"DE\",\n" +
"        \"salePrice\": \"SEK98\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YA_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"YA\",\n" +
"        \"country\": \"SE\",\n" +
"        \"salePrice\": \"SEK160\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YQ\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YQ\",\n" +
"        \"salePrice\": \"SEK380\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YQ\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YQ\",\n" +
"        \"salePrice\": \"SEK1289\"\n" +
"       }\n" +
"      ],\n" +
"      \"fareCalculation\": \"STO SK X/DUS EY X/AUH EY SGN 262.46VLAPOWSE NUC 262.46 END ROE 7.08276 FARE SEK 1859 XT 160YA 98RA 1669YQ\",\n" +
"      \"latestTicketingTime\": \"2014-11-04T11:09-04:00\",\n" +
"      \"ptc\": \"ADT\"\n" +
"     }\n" +
"    ]\n" +
"   },\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK7622\",\n" +
"    \"id\": \"afJyHYtK0GJQmQI2hd2s9O001\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 1040,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 95,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"AB\",\n" +
"         \"number\": \"8109\"\n" +
"        },\n" +
"        \"id\": \"Gm7U5imAYVomyaBb\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"Q\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"Ly+FqC-ELgL04Zwo\",\n" +
"          \"aircraft\": \"738\",\n" +
"          \"arrivalTime\": \"2014-11-04T20:50+01:00\",\n" +
"          \"departureTime\": \"2014-11-04T19:15+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"TXL\",\n" +
"          \"originTerminal\": \"2\",\n" +
"          \"duration\": 95,\n" +
"          \"mileage\": 520,\n" +
"          \"meal\": \"Snack or Brunch\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 60\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 360,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"AB\",\n" +
"         \"number\": \"7494\"\n" +
"        },\n" +
"        \"id\": \"GKz0cmpANlbWAxej\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"Q\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LI5LFWrQpQ5NPpvI\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T06:50+04:00\",\n" +
"          \"departureTime\": \"2014-11-04T21:50+01:00\",\n" +
"          \"origin\": \"TXL\",\n" +
"          \"destination\": \"AUH\",\n" +
"          \"destinationTerminal\": \"1\",\n" +
"          \"duration\": 360,\n" +
"          \"mileage\": 2896,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 100\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 425,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"AB\",\n" +
"         \"number\": \"4110\"\n" +
"        },\n" +
"        \"id\": \"GzK1kwkHthZJNxBv\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"Q\",\n" +
"        \"bookingCodeCount\": 4,\n" +
"        \"marriedSegmentGroup\": \"2\",\n" +
"        \"subjectToGovernmentApproval\": true,\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LtLybmk9aMf6cEpz\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T18:35+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T08:30+04:00\",\n" +
"          \"origin\": \"AUH\",\n" +
"          \"destination\": \"SGN\",\n" +
"          \"originTerminal\": \"3\",\n" +
"          \"destinationTerminal\": \"2\",\n" +
"          \"duration\": 425,\n" +
"          \"operatingDisclosure\": \"OPERATED BY ETIHAD AIRWAYS\",\n" +
"          \"mileage\": 3528\n" +
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
"        \"id\": \"APTyg+phci48fOmknLPAyWNNfe+as4rtbcpRXA6BgLQ6\",\n" +
"        \"carrier\": \"AB\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"SGN\",\n" +
"        \"basisCode\": \"QLRCOWSE\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"APTyg+phci48fOmknLPAyWNNfe+as4rtbcpRXA6BgLQ6\",\n" +
"        \"segmentId\": \"GzK1kwkHthZJNxBv\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"APTyg+phci48fOmknLPAyWNNfe+as4rtbcpRXA6BgLQ6\",\n" +
"        \"segmentId\": \"GKz0cmpANlbWAxej\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"APTyg+phci48fOmknLPAyWNNfe+as4rtbcpRXA6BgLQ6\",\n" +
"        \"segmentId\": \"Gm7U5imAYVomyaBb\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK2209\",\n" +
"      \"saleFareTotal\": \"SEK2209\",\n" +
"      \"saleTaxTotal\": \"SEK1602\",\n" +
"      \"saleTotal\": \"SEK3811\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 2\n" +
"      },\n" +
"      \"tax\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"DE_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"DE\",\n" +
"        \"country\": \"DE\",\n" +
"        \"salePrice\": \"SEK56\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"RA_2\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"RA\",\n" +
"        \"country\": \"DE\",\n" +
"        \"salePrice\": \"SEK97\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YA_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"YA\",\n" +
"        \"country\": \"SE\",\n" +
"        \"salePrice\": \"SEK160\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YQ\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YQ\",\n" +
"        \"salePrice\": \"SEK1289\"\n" +
"       }\n" +
"      ],\n" +
"      \"fareCalculation\": \"STO AB X/BER AB X/AUH AB SGN 311.88QLRCOWSE NUC 311.88 END ROE 7.08276 FARE SEK 2209 XT 160YA 56DE 97RA 1289YQ\",\n" +
"      \"latestTicketingTime\": \"2014-10-28T20:27-04:00\",\n" +
"      \"ptc\": \"ADT\",\n" +
"      \"refundable\": true\n" +
"     }\n" +
"    ]\n" +
"   },\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK8500\",\n" +
"    \"id\": \"afJyHYtK0GJQmQI2hd2s9O002\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 1115,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 205,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"1796\"\n" +
"        },\n" +
"        \"id\": \"GPUC3xQXcHra-3Fw\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LmgcQHIVp6t6ft90\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-04T21:45+02:00\",\n" +
"          \"departureTime\": \"2014-11-04T17:20+01:00\",\n" +
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
"        \"duration\": 720,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"68\"\n" +
"        },\n" +
"        \"id\": \"GSbRkB8TqTQCrRvD\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 3,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LGnyXNoX8UYBJucz\",\n" +
"          \"aircraft\": \"343\",\n" +
"          \"arrivalTime\": \"2014-11-05T14:50+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T00:55+02:00\",\n" +
"          \"origin\": \"IST\",\n" +
"          \"destination\": \"BKK\",\n" +
"          \"originTerminal\": \"I\",\n" +
"          \"duration\": 535,\n" +
"          \"mileage\": 4662,\n" +
"          \"meal\": \"Meal\",\n" +
"          \"connectionDuration\": 85\n" +
"         },\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LULJF2CATjTyyA1w\",\n" +
"          \"aircraft\": \"343\",\n" +
"          \"arrivalTime\": \"2014-11-05T17:55+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T16:15+07:00\",\n" +
"          \"origin\": \"BKK\",\n" +
"          \"destination\": \"SGN\",\n" +
"          \"duration\": 100,\n" +
"          \"mileage\": 444,\n" +
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
"        \"id\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"carrier\": \"TK\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"SGN\",\n" +
"        \"basisCode\": \"EN2PXOW\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"segmentId\": \"GPUC3xQXcHra-3Fw\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"segmentId\": \"GSbRkB8TqTQCrRvD\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK2580\",\n" +
"      \"saleFareTotal\": \"SEK2580\",\n" +
"      \"saleTaxTotal\": \"SEK1670\",\n" +
"      \"saleTotal\": \"SEK4250\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 2\n" +
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
"      \"fareCalculation\": \"STO TK X/IST TK SGN 364.26EN2PXOW NUC 364.26 END ROE 7.08276 FARE SEK 2580 XT 160YA 46TR 1464YR\",\n" +
"      \"latestTicketingTime\": \"2014-10-28T23:59-04:00\",\n" +
"      \"ptc\": \"ADT\",\n" +
"      \"refundable\": true\n" +
"     }\n" +
"    ]\n" +
"   },\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK8500\",\n" +
"    \"id\": \"afJyHYtK0GJQmQI2hd2s9O005\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 1425,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 205,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"1794\"\n" +
"        },\n" +
"        \"id\": \"GThtClvCh0FkrEnM\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 9,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"L79EI6gpqcrSSJpc\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-04T16:35+02:00\",\n" +
"          \"departureTime\": \"2014-11-04T12:10+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"IST\",\n" +
"          \"originTerminal\": \"5\",\n" +
"          \"destinationTerminal\": \"I\",\n" +
"          \"duration\": 205,\n" +
"          \"mileage\": 1372,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 500\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 720,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"TK\",\n" +
"         \"number\": \"68\"\n" +
"        },\n" +
"        \"id\": \"GSbRkB8TqTQCrRvD\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"E\",\n" +
"        \"bookingCodeCount\": 3,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LGnyXNoX8UYBJucz\",\n" +
"          \"aircraft\": \"343\",\n" +
"          \"arrivalTime\": \"2014-11-05T14:50+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T00:55+02:00\",\n" +
"          \"origin\": \"IST\",\n" +
"          \"destination\": \"BKK\",\n" +
"          \"originTerminal\": \"I\",\n" +
"          \"duration\": 535,\n" +
"          \"mileage\": 4662,\n" +
"          \"meal\": \"Meal\",\n" +
"          \"connectionDuration\": 85\n" +
"         },\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LULJF2CATjTyyA1w\",\n" +
"          \"aircraft\": \"343\",\n" +
"          \"arrivalTime\": \"2014-11-05T17:55+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T16:15+07:00\",\n" +
"          \"origin\": \"BKK\",\n" +
"          \"destination\": \"SGN\",\n" +
"          \"duration\": 100,\n" +
"          \"mileage\": 444,\n" +
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
"        \"id\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"carrier\": \"TK\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"SGN\",\n" +
"        \"basisCode\": \"EN2PXOW\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"segmentId\": \"GThtClvCh0FkrEnM\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"AXPBFL2Hpci0AxxvB0Zv3Ovnu0uh32BVFwydmk4oqynM\",\n" +
"        \"segmentId\": \"GSbRkB8TqTQCrRvD\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK2580\",\n" +
"      \"saleFareTotal\": \"SEK2580\",\n" +
"      \"saleTaxTotal\": \"SEK1670\",\n" +
"      \"saleTotal\": \"SEK4250\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 2\n" +
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
"      \"fareCalculation\": \"STO TK X/IST TK SGN 364.26EN2PXOW NUC 364.26 END ROE 7.08276 FARE SEK 2580 XT 160YA 46TR 1464YR\",\n" +
"      \"latestTicketingTime\": \"2014-10-28T23:59-04:00\",\n" +
"      \"ptc\": \"ADT\",\n" +
"      \"refundable\": true\n" +
"     }\n" +
"    ]\n" +
"   },\n" +
"   {\n" +
"    \"kind\": \"qpxexpress#tripOption\",\n" +
"    \"saleTotal\": \"SEK9722\",\n" +
"    \"id\": \"afJyHYtK0GJQmQI2hd2s9O003\",\n" +
"    \"slice\": [\n" +
"     {\n" +
"      \"kind\": \"qpxexpress#sliceInfo\",\n" +
"      \"duration\": 1040,\n" +
"      \"segment\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 95,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"EY\",\n" +
"         \"number\": \"1689\"\n" +
"        },\n" +
"        \"id\": \"GLrfyBChoo2cYm5M\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"M\",\n" +
"        \"bookingCodeCount\": 4,\n" +
"        \"marriedSegmentGroup\": \"0\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LCA7sxQTmd5cMUcg\",\n" +
"          \"aircraft\": \"738\",\n" +
"          \"arrivalTime\": \"2014-11-04T20:50+01:00\",\n" +
"          \"departureTime\": \"2014-11-04T19:15+01:00\",\n" +
"          \"origin\": \"ARN\",\n" +
"          \"destination\": \"TXL\",\n" +
"          \"originTerminal\": \"2\",\n" +
"          \"duration\": 95,\n" +
"          \"operatingDisclosure\": \"OPERATED BY AIR BERLIN PLC & CO. LUFTVERKEHRS KG\",\n" +
"          \"mileage\": 520,\n" +
"          \"meal\": \"Snack or Brunch\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 60\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 360,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"EY\",\n" +
"         \"number\": \"1402\"\n" +
"        },\n" +
"        \"id\": \"GQdVwJyyb8W3sGHi\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"M\",\n" +
"        \"bookingCodeCount\": 2,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LIN7waHcfd-1b7wf\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T06:50+04:00\",\n" +
"          \"departureTime\": \"2014-11-04T21:50+01:00\",\n" +
"          \"origin\": \"TXL\",\n" +
"          \"destination\": \"AUH\",\n" +
"          \"destinationTerminal\": \"1\",\n" +
"          \"duration\": 360,\n" +
"          \"operatingDisclosure\": \"OPERATED BY AIR BERLIN PLC & CO. LUFTVERKEHRS KG\",\n" +
"          \"mileage\": 2896,\n" +
"          \"meal\": \"Meal\"\n" +
"         }\n" +
"        ],\n" +
"        \"connectionDuration\": 100\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentInfo\",\n" +
"        \"duration\": 425,\n" +
"        \"flight\": {\n" +
"         \"carrier\": \"EY\",\n" +
"         \"number\": \"441\"\n" +
"        },\n" +
"        \"id\": \"GnNxf0XrHhl0G7kp\",\n" +
"        \"cabin\": \"COACH\",\n" +
"        \"bookingCode\": \"M\",\n" +
"        \"bookingCodeCount\": 2,\n" +
"        \"marriedSegmentGroup\": \"1\",\n" +
"        \"leg\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#legInfo\",\n" +
"          \"id\": \"LAZ5JmJsGqKdgdkW\",\n" +
"          \"aircraft\": \"332\",\n" +
"          \"arrivalTime\": \"2014-11-05T18:35+07:00\",\n" +
"          \"departureTime\": \"2014-11-05T08:30+04:00\",\n" +
"          \"origin\": \"AUH\",\n" +
"          \"destination\": \"SGN\",\n" +
"          \"originTerminal\": \"3\",\n" +
"          \"destinationTerminal\": \"2\",\n" +
"          \"duration\": 425,\n" +
"          \"mileage\": 3528,\n" +
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
"        \"id\": \"Aq513tIdLgWUrTiz6pEHtsaMV6GNddVW8TYISXUPA7lE\",\n" +
"        \"carrier\": \"EY\",\n" +
"        \"origin\": \"STO\",\n" +
"        \"destination\": \"SGN\",\n" +
"        \"basisCode\": \"MLOWSE\"\n" +
"       }\n" +
"      ],\n" +
"      \"segmentPricing\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"Aq513tIdLgWUrTiz6pEHtsaMV6GNddVW8TYISXUPA7lE\",\n" +
"        \"segmentId\": \"GnNxf0XrHhl0G7kp\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"Aq513tIdLgWUrTiz6pEHtsaMV6GNddVW8TYISXUPA7lE\",\n" +
"        \"segmentId\": \"GQdVwJyyb8W3sGHi\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#segmentPricing\",\n" +
"        \"fareId\": \"Aq513tIdLgWUrTiz6pEHtsaMV6GNddVW8TYISXUPA7lE\",\n" +
"        \"segmentId\": \"GLrfyBChoo2cYm5M\",\n" +
"        \"freeBaggageOption\": [\n" +
"         {\n" +
"          \"kind\": \"qpxexpress#freeBaggageAllowance\",\n" +
"          \"kilos\": 30\n" +
"         }\n" +
"        ]\n" +
"       }\n" +
"      ],\n" +
"      \"baseFareTotal\": \"SEK3259\",\n" +
"      \"saleFareTotal\": \"SEK3259\",\n" +
"      \"saleTaxTotal\": \"SEK1602\",\n" +
"      \"saleTotal\": \"SEK4861\",\n" +
"      \"passengers\": {\n" +
"       \"kind\": \"qpxexpress#passengerCounts\",\n" +
"       \"adultCount\": 2\n" +
"      },\n" +
"      \"tax\": [\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"DE_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"DE\",\n" +
"        \"country\": \"DE\",\n" +
"        \"salePrice\": \"SEK56\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"RA_2\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"RA\",\n" +
"        \"country\": \"DE\",\n" +
"        \"salePrice\": \"SEK97\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YA_1\",\n" +
"        \"chargeType\": \"GOVERNMENT\",\n" +
"        \"code\": \"YA\",\n" +
"        \"country\": \"SE\",\n" +
"        \"salePrice\": \"SEK160\"\n" +
"       },\n" +
"       {\n" +
"        \"kind\": \"qpxexpress#taxInfo\",\n" +
"        \"id\": \"YQ\",\n" +
"        \"chargeType\": \"CARRIER_SURCHARGE\",\n" +
"        \"code\": \"YQ\",\n" +
"        \"salePrice\": \"SEK1289\"\n" +
"       }\n" +
"      ],\n" +
"      \"fareCalculation\": \"STO EY X/BER EY X/AUH EY SGN 460.13MLOWSE NUC 460.13 END ROE 7.08276 FARE SEK 3259 XT 160YA 56DE 97RA 1289YQ\",\n" +
"      \"latestTicketingTime\": \"2014-11-04T13:14-04:00\",\n" +
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
