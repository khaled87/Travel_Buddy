package travelbuddy.entity;

import javax.persistence.Entity;

@Entity
public class Hotel extends AbstractEntity {

    private String name;
    private String address1;
    private double price;
    
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
}
