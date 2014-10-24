/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class FlightInfo {

     private List<Trip> slice = new ArrayList<>();
     
    /*private String arrivalTime;
    private String departureTime;*/
   // private String origin;
    //private String destination;
    /*private String destinationAirport;
    private String originAirport;
    private String meal;
    private String saleTotal;
*/
    /* public FlightInfo(String arrivalTime,String departureTime,String origin, String destination, String destinationAirport,String originAirport,String meal){
     this.arrivalTime = arrivalTime;
     this.departureTime = departureTime;
     this.origin = origin;
     this.destination = destination;
     this.destinationAirport = destinationAirport;
     this.originAirport = originAirport;
     this.meal = meal;
     }
     */

    public List<Trip> getSlice() {
        return slice;
    }

    public void setSlice(List<Trip> slice) {
        this.slice = slice;
    }
    
 


  
}
