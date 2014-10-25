package travelbuddy.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Trip extends AbstractEntity {

    @OneToMany
    private List<SubTrip> subtrips;
    private String saleTotal;
    private String maxFreeBaggage;

    public List<SubTrip> getSubtrips() {
        return subtrips;
    }

    public void setSubtrips(List<SubTrip> subtrips) {
        this.subtrips = subtrips;
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
