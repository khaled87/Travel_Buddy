package travelbuddy.entity;

import javax.json.JsonObject;
import javax.persistence.Entity;

@Entity
public class Hotel extends AbstractEntity {

    private String name;
    private String address1;
    private double price;
    private String shortDescription;
    private String thumbNailUrl;
    private double hotelRating;
    
    public Hotel()
    {
        
    }
 
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress1() {
        return address1;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

    public double getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(double hotelRating) {
        this.hotelRating = hotelRating;
    }
        
    public static Hotel fromJson(JsonObject jHotel) {
        if (jHotel == null) {
            return null;
        }
        Hotel nHotel = new Hotel();
        nHotel.setName(jHotel.getString("name", ""));
        nHotel.setAddress1(jHotel.getString("address1", ""));
        nHotel.setPrice(jHotel.getInt("price", 0));
        return nHotel;
    }
}
