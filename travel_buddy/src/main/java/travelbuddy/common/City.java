package travelbuddy.common;

import java.util.HashMap;

/**
* The city class contains all of the supported cities and their codes.
* By now we don't have a convenient way to convert city names to city codes.
*/
public class City {
    HashMap cities = new HashMap();

    public void createAbbrevList() {
        cities.put("gothenburg","got");
        cities.put("stockholm","sto");
        cities.put("berlin", "TXL");
        cities.put("stockholm", "sto");
        cities.put("new york", "nyc");
        cities.put("saigon", "sgn");
        cities.put("malmo", "mmx");
        cities.put("malmö", "mmx");
        cities.put("oslo", "OSL");
        cities.put("bangkok", "BKK");
        cities.put("Shanghai", "PVG");
        cities.put("new delhi", "del");
        cities.put("mexico", "ACA");
        cities.put("antalya", "AYT");
        cities.put("tel aviv", "TLV");
        cities.put("tehran", "THR");
        cities.put("paris", "PAR");
        cities.put("warsaw", "WAW");
        cities.put("rome", "ROM");
        cities.put("moscow", "MOW");
    }

    public String getCityAbbrev(String city) {
        System.out.println("yes" + cities.get(city));
        return cities.get(city.toLowerCase()).toString();
    }

}
