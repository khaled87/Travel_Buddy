package travelbuddy.entity;

import java.util.List;
import javax.persistence.Entity;
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
    
    public Product() {
        
    }

    public Product(String name, double price, String description, List<Flight> flights, Hotel hotel) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
      //  this.flights = flights;
    }

    public Product(Long id, String name, double price, List<Flight> flights, Hotel hotel) {
        super(id);
        this.name = name;
        this.price = price;
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
}
