package travelbuddy.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * A Product
 * @author hajo
 */
@Entity
public class Product extends AbstractEntity {

    private String name;
    private double price;
    @Column(columnDefinition="CLOB") 
    @Lob 
    private String description;
    @OneToMany
    private List<Trip> trips;
    @OneToOne
    private Hotel hotel;
    @Column(columnDefinition="CLOB") 
    @Lob 
    private String img;
    
    public Product() {
        
    }

    public Product(String name, double price, String description, List<Trip> trips, Hotel hotel, String img) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
        this.trips = trips;
        this.img = img;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
     
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + '}';
    }

    public List<Trip> getFlights() {
        return getTrips();
    }

    public Hotel getHotel() {
        return hotel;
    }
    
    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
