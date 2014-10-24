/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelbuddy.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class Trip {

    private List<SubTrip> subTripList;
    private String saleTotal;
    private String maxFreeBaggage;

    public Trip(){
        
    }
    public List<SubTrip> getSubTripList() {
        return new ArrayList<>(this.subTripList);
        //subTripList;
    }

    public void setSubTripList(List<SubTrip> list) {
        // this.subTripList = subTripList;
        this.subTripList = new ArrayList<>(list);
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

}
