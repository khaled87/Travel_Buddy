package travelbuddy.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PurchaseOrder extends AbstractEntity {
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    @ManyToOne
    private Product product;
    private String userFullName;
    private String userEmail;
    private String confirmationCode;
    private String hotelConfirmationCode;
    private String flight1ConfirmationCode;
    private String flight2ConfirmationCode;
    
    public PurchaseOrder() { } 

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + getDate()
                + ", product=" + getProduct() + "}";
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product p) {
        this.product = p;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    
    public String getConfirmationCode() {
        return this.confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
    
    public String getHotelConfirmationCode() {
        return hotelConfirmationCode;
    }

    public void setHotelConfirmationCode(String hotelConfirmationCode) {
        this.hotelConfirmationCode = hotelConfirmationCode;
    }
        
    public String getFlight1ConfirmationCode() {
        return flight1ConfirmationCode;
    }

    public void setFlight1ConfirmationCode(String flight1ConfirmationCode) {
        this.flight1ConfirmationCode = flight1ConfirmationCode;
    }
        
    public String getFlight2ConfirmationCode() {
        return flight2ConfirmationCode;
    }

    public void setFlight2ConfirmationCode(String flight2ConfirmationCode) {
        this.flight2ConfirmationCode = flight2ConfirmationCode;
    }
}
