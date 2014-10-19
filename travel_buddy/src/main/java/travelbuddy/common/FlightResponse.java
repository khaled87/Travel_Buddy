/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.common;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Ali
 */
public class FlightResponse {

    private String kind;
    private Trips trips;

    public FlightResponse(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return kind + getTrips() + " ";
    }

    public Trips getTrips() {
        return trips;
    }

    public void setTrips(Trips trips) {
        this.trips = trips;
    }

    public static class Trips {

        private String kind;
        private Data data;
        private List<Tripoption> tripOption = new ArrayList<>();
        Leg leg = new Leg();
        private String s = leg.getArrivalTime();

        @Override
        public String toString() {
            return getKind() + " " + getData() + getTripOption().get(0);
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public List<Tripoption> getTripOption() {
            return tripOption;
        }

        public void setTripOption(List<Tripoption> tripOption) {
            this.tripOption = tripOption;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }

    public static class Data {

        private String kind;

        private List<Airport> airport = new ArrayList<>();
        private List<Carrier> carrier = new ArrayList<>();

        @Override
        public String toString() {
            return getKind() + " from" + " " + getAirport().get(0).getName() + " to " + getAirport().get(1) + " " + getCarrier().get(0);
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public List<Airport> getAirport() {
            return airport;
        }

        public void setAirport(List<Airport> airport) {
            this.airport = airport;
        }

        public List<Carrier> getCarrier() {
            return carrier;
        }

        public void setCarrier(List<Carrier> carrier) {
            this.carrier = carrier;
        }
    }

    public static class Airport {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class Carrier {

        //flight company
        private String name;

        @Override
        public String toString() {
            return getName();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Slice {

        private List<Segment> segment = new ArrayList<>();

        public List<Segment> getSegment() {
            return segment;
        }

        public void setSegment(List<Segment> segment) {
            this.segment = segment;
        }

        @Override
        public String toString() {
            for (int i = 0; i < getSegment().size(); i++) {
                return "Cabin " + getSegment().get(i).getCabin() + " arrivalTime"
                        + getSegment().get(i).getLeg().get(i).getArrivalTime() + " Meal"
                        + getSegment().get(i).getLeg().get(i).getMeal();
            }
            return null;

        }

    }

    public static class Segment {

        private String cabin;
        private List<Leg> leg = new ArrayList<>();

        public String getCabin() {
            return cabin;
        }

        public void setCabin(String cabin) {
            this.cabin = cabin;
        }

        public List<Leg> getLeg() {
            return leg;
        }

        public void setLeg(List<Leg> leg) {
            this.leg = leg;
        }

    }

    public static class Tripoption {

        private String saleTotal;
        private List<Slice> slice = new ArrayList<>();

        @Override
        public String toString() {
            return "price " + getSaleTotal() + getSlice().get(0);
        }

        public String getSaleTotal() {
            return saleTotal;
        }

        public void setSaleTotal(String saleTotal) {
            this.saleTotal = saleTotal;
        }

        public List<Slice> getSlice() {
            return slice;
        }

        public void setSlice(List<Slice> slice) {
            this.slice = slice;
        }
    }

    public static class Leg {

        private String arrivalTime;
        private String departureTime;
        private String meal;

        public String getArrivalTime() {
            return arrivalTime;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public String getMeal() {
            return meal;
        }

        @Override
        public String toString() {
            return "depTime" + getDepartureTime() + "ArivTime" + getArrivalTime();
        }
    }

}
