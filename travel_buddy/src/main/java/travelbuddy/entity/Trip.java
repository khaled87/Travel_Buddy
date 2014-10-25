package travelbuddy.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Trip extends AbstractEntity {

    @OneToMany
    private List<SubTrip> subTrips;
    private String saleTotal;
    private String maxFreeBaggage;

    public List<SubTrip> getSubTrips() {
        return subTrips;
    }

    public void setSubTrips(List<SubTrip> subTrips) {
        this.subTrips = subTrips;
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
