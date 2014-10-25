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
    private List<Trip> trips;
    @OneToOne
    private Hotel hotel;
    @Column(columnDefinition="CLOB NOT NULL") 
    @Lob 
    private String img;
    
    public Product() {
        
    }

<<<<<<< HEAD
    public Product(String name, double price, String description, List<Flight> flights, Hotel hotel, String img) {
=======
    public Product(String name, double price, String description, List<Trip> trips, Hotel hotel) {
>>>>>>> 355218538df796b156c5015e559c62ea44dfb851
        this.name = name;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
<<<<<<< HEAD
        this.flights = flights;
        this.img = img;
=======
      //  this.flights = flights;
    }

    public Product(Long id, String name, double price, List<Trip> trips, Hotel hotel) {
        super(id);
        this.name = name;
        this.price = price;
>>>>>>> 355218538df796b156c5015e559c62ea44dfb851
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
        return trips;
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
