/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.common;

import java.util.HashMap;
import java.util.Hashtable;


public class City {

    /*public static void main(String[] args) {
        City city = new City();
        city.setAbbrev();
        city.getCityAbbrev("gOthenburg");
    }*/
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
