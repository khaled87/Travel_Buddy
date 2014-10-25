package travelbuddy.entity;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Trip extends AbstractEntity {

    @OneToMany
    private List<SubTrip> subtrips;
    private String saleTotal;
    private String maxFreeBaggage;

    public List<SubTrip> getSubtripList() {
       return new ArrayList<>(this.subtrips);
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

  
    public void setSubtripList(List<SubTrip> subtrip) {
        this.subtrips = new ArrayList<>(subtrip);
    }
     
       
    

  

  

   

}
