package travelbuddy.entity;

import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Trip extends AbstractEntity {

    @OneToMany
    private List<SubTrip> subtrips;
    private String saleTotal;
    private String maxFreeBaggage;

    public List<SubTrip> getSubtripList() {
       return new ArrayList<>(this.subtrips);
    }

    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
    }

    public String getMaxFreeBaggage() {
        return maxFreeBaggage;
    }

    public void setMaxFreeBaggage(String maxFreeBaggage) {
        this.maxFreeBaggage = maxFreeBaggage;
    }
  
    public void setSubtripList(List<SubTrip> subtrip) {
        this.subtrips = new ArrayList<>(subtrip);
    }

    public String getShortDescription() {
        if (subtrips == null || subtrips.isEmpty()) {
            return "";
        }
        SubTrip firstSub = subtrips.get(0);
        SubTrip lastSub = subtrips.get(subtrips.size() - 1);
        return "From: " + firstSub.getOrigin() + " " + firstSub.getDepartureTime() 
                + " To: " + lastSub.getDestination() + " " + lastSub.getArrivalTime()
                + " " + saleTotal;
    }
    
    public void setShortDescription(String s) {
        // do nothing
    }
    
    public double getPrice() {
        return Double.parseDouble(saleTotal.replace("SEK", ""));
    }
    
    public void setPrice(double price) {
        // do nothing
    }
    
    public static Trip fromJson(JsonObject jTrip) {
        if (jTrip == null) {
            return null;
        }
        Trip trip = new Trip();
        trip.maxFreeBaggage = jTrip.getString("maxFreeBaggage", "0");
        trip.saleTotal = jTrip.getString("saleTotal", "SEK0");
//        JsonArray subtrips = jTrip.getJsonArray("subtripList");
//        for (JsonValue subtrip : subtrips) {
//            // TODO
//        }
        return trip;
    }
}
