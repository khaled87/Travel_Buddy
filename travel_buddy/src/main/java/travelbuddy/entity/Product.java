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
    private String description;
    @OneToMany
    private List<Flight> flights;
    @OneToOne
    private Hotel hotel;
    @Column(columnDefinition="CLOB NOT NULL") 
    @Lob 
    private String img;
    
    public Product() {
        
    }

    public Product(String name, double price, String description, List<Flight> flights, Hotel hotel, String img) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
        this.flights = flights;
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

    public List<Flight> getFlights() {
        return flights;
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

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
