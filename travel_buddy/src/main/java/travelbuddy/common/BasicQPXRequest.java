package travelbuddy.common;

import javax.json.Json;
import javax.json.JsonObject;

public class BasicQPXRequest {

    //private final String apiKey = "AIzaSyBK9KNDV3W5tIc121fI07eRYDGcT3yGUJU";
    private int adultCount;
    private int childCount;
    private int infantInSeatCount;
    private String preferredCabin;
    private String origin;
    private String destination;
    private String date;
    private String solution;
    private String maxPrice;

  
//    public String getApiKey() {
//        return apiKey;
//    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getInfantInSeatCount() {
        return infantInSeatCount;
    }

    public void setInfantInSeatCount(int infantInSeatCount) {
        this.infantInSeatCount = infantInSeatCount;
    }

    public String getPreferredCabin() {
        return preferredCabin;
    }

    public void setPreferredCabin(String preferredCabin) {
        this.preferredCabin = preferredCabin;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String toParam() {
        JsonObject object = Json.createObjectBuilder()
                .add("request", Json.createObjectBuilder().
                        add("passengers", Json.createObjectBuilder().
                                add("adultCount", getAdultCount())
                                .add("childCount", getChildCount())
                                .add("infantInSeatCount", getInfantInSeatCount()))
                        .add("slice", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder().
                                        add("origin", getOrigin())
                                        .add("destination", getDestination())
                                        .add("date", getDate())
                                        .add("preferredCabin", getPreferredCabin())))
                        .add("maxPrice", getMaxPrice())
                        .add("solutions", getSolution())).build();

        return object.toString();
    }

}
