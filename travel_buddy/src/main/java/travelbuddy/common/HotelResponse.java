package travelbuddy.common;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import travelbuddy.entity.Hotel;

// Contains hotel information received from EAN
// Documentation can be found here: http://developer.ean.com/docs/hotel-list/
public class HotelResponse {
    
    public ArrayList<Hotel> getHotelList(){
         ArrayList<Hotel> innerHotels = new ArrayList<>();
        for(HotelSummary hs : this.getHotelListResponse().getHotelList().getHotelSummary())
        {
            Hotel hotel = new Hotel();
            hotel.setName(hs.name);
            hotel.setAddress1(hs.address1);
            hotel.setPrice(hs.highRate);
            hotel.setShortDescription(hs.shortDescription);
            hotel.setThumbNailUrl("http://media.expedia.com" + hs.thumbNailUrl);
            hotel.setHotelRating(hs.hotelRating);
            innerHotels.add(hotel);
        }
        
        return innerHotels;
    }
    
    public HotelListResponse getHotelListResponse() {
        return HotelListResponse;
    }

    public class Surcharge {

        @SerializedName("@type")
        private String type;
        @SerializedName("@amount")
        private double amount;

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }
    }

    public class Surcharges {

        private Surcharge Surcharge;

        public Surcharge getSurcharge() {
            return Surcharge;
        }
    }

    public class NightlyRate {

        @SerializedName("@baseRate")
        private double baseRate;
        @SerializedName("@rate")
        private double rate;
        @SerializedName("@promo")
        private boolean promo;

        public double getBaseRate() {
            return baseRate;
        }

        public double getRate() {
            return rate;
        }

        public boolean isPromo() {
            return promo;
        }
    }

    public class NightlyRatesPerRoom {

        private NightlyRate NightlyRate;

        public NightlyRate getNightlyRate() {
            return NightlyRate;
        }
    }

    public class ChargeableRateInfo {

        @SerializedName("@averageBaseRate")
        private double averageBaseRate;
        @SerializedName("@averageRate")
        private double averageRate;
        @SerializedName("@commissionableUsdTotal")
        private double commissionableUsdTotal;
        @SerializedName("@maxNightlyRate")
        private double maxNightlyRate;
        @SerializedName("@nightlyRateTotal")
        private double nightlyRateTotal;
        @SerializedName("@surchargeTotal")
        private double surchargeTotal;
        @SerializedName("@total")
        private double total;
        @SerializedName("@currencyCode")
        private String currencyCode;
        private NightlyRatesPerRoom NightlyRatesPerRoom;
        private Surcharges Surcharges;

        public double getAverageBaseRate() {
            return averageBaseRate;
        }

        public double getAverageRate() {
            return averageRate;
        }

        public double getCommissionableUsdTotal() {
            return commissionableUsdTotal;
        }

        public double getMaxNightlyRate() {
            return maxNightlyRate;
        }

        public double getNightlyRateTotal() {
            return nightlyRateTotal;
        }

        public double getSurchargeTotal() {
            return surchargeTotal;
        }

        public double getTotal() {
            return total;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public NightlyRatesPerRoom getNightlyRatesPerRoom() {
            return NightlyRatesPerRoom;
        }

        public Surcharges getSurcharges() {
            return Surcharges;
        }
    }

    public class RateInfo {

        private ChargeableRateInfo ChargeableRateInfo;

        public ChargeableRateInfo getChargeableRateInfo() {
            return ChargeableRateInfo;
        }
    }

    public class RoomRateDetails {

        private int roomTypeCode;
        private int rateCode;
        private int maxRoomOccupancy;
        private int quotedRoomOccupancy;
        private int minGuestAge;
        private String roomDescription;
        private boolean propertyAvailable;
        //expediaPropertyId is hotelId
        private int expediaPropertyId;
        private RateInfo RateInfo;

        public int getRoomTypeCode() {
            return roomTypeCode;
        }

        public int getRateCode() {
            return rateCode;
        }

        public int getMaxRoomOccupancy() {
            return maxRoomOccupancy;
        }

        public int getQuotedRoomOccupancy() {
            return quotedRoomOccupancy;
        }

        public int getMinGuestAge() {
            return minGuestAge;
        }

        public String getRoomDescription() {
            return roomDescription;
        }

        public boolean isPropertyAvailable() {
            return propertyAvailable;
        }

        public int getExpediaPropertyId() {
            return expediaPropertyId;
        }

        public RateInfo getRateInfo() {
            return RateInfo;
        }
    }

    public class RoomRateDetailsList {

        private RoomRateDetails RoomRateDetails;

        public RoomRateDetails getRoomRateDetails() {
            return RoomRateDetails;
        }
    }

    public class HotelSummary {

        private int hotelId;
        private String name;
        private String address1;
        private String city;
        private String stateProvinceCode;
        private int postalCode;
        private String countryCode;
        private String airportCode;
        private String supplierType;
        private double hotelRating;
        private String shortDescription;
        private double highRate;
        private double lowRate;
        private String rateCurrencyCode;
        private double latitude;
        private double longitude;
        private double proximityDistance;
        private String proximityUnit;
        private String thumbNailUrl;
        private RoomRateDetailsList RoomRateDetailsList;

        public int getHotelId() {
            return hotelId;
        }

        public String getName() {
            return name;
        }

        public String getAddress1() {
            return address1;
        }

        public String getCity() {
            return city;
        }

        public String getStateProvinceCode() {
            return stateProvinceCode;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getAirportCode() {
            return airportCode;
        }

        public String getSupplierType() {
            return supplierType;
        }

        public double getHotelRating() {
            return hotelRating;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public double getHighRate() {
            return highRate;
        }

        public double getLowRate() {
            return lowRate;
        }

        public String getRateCurrencyCode() {
            return rateCurrencyCode;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getProximityDistance() {
            return proximityDistance;
        }

        public String getProximityUnit() {
            return proximityUnit;
        }

        public String getThumbNailUrl() {
            return thumbNailUrl;
        }

        public RoomRateDetailsList getRoomRateDetailsList() {
            return RoomRateDetailsList;
        }
    }

    public class HotelList {

        private ArrayList<HotelSummary> HotelSummary;

        public ArrayList<HotelSummary> getHotelSummary() {
            return HotelSummary;
        }
    }

    public class HotelListResponse {

        private String cacheKey;
        private String cacheLocation;
        private String customerSessionId;
        private int numberOfRoomsRequested;
        private boolean moreResultsAvailable;
        private HotelList HotelList;

        public String getCacheKey() {
            return cacheKey;
        }

        public void setCacheKey(String cacheKey) {
            this.cacheKey = cacheKey;
        }

        public String getCacheLocation() {
            return cacheLocation;
        }

        public String getCustomerSessionId() {
            return customerSessionId;
        }

        public int getNumberOfRoomsRequested() {
            return numberOfRoomsRequested;
        }

        public boolean isMoreResultsAvailable() {
            return moreResultsAvailable;
        }

        public HotelList getHotelList() {
            return HotelList;
        }

    }
    private HotelListResponse HotelListResponse;

}
