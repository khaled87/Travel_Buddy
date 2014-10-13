package travelbuddy.common;

import java.util.Date;

public class HotelRequest extends BasicRequest {
    private String city;
    private String stateProvinceCode;
    private String countryCode;
    private String supplierCacheTolerance;
    private Date arrivalDate;
    private Date departureDate;
    private Integer room1;
    private Integer room2;
    private Integer room3;
    private Integer room4;
    private Integer numberOfResults;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSupplierCacheTolerance() {
        return supplierCacheTolerance;
    }

    public void setSupplierCacheTolerance(String supplierCacheTolerance) {
        this.supplierCacheTolerance = supplierCacheTolerance;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getRoom1() {
        return room1;
    }

    public void setRoom1(Integer room1) {
        this.room1 = room1;
    }

    public Integer getRoom2() {
        return room2;
    }

    public void setRoom2(Integer room2) {
        this.room2 = room2;
    }

    public Integer getRoom3() {
        return room3;
    }

    public void setRoom3(Integer room3) {
        this.room3 = room3;
    }

    public Integer getRoom4() {
        return room4;
    }

    public void setRoom4(Integer room4) {
        this.room4 = room4;
    }

    public Integer getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

}
