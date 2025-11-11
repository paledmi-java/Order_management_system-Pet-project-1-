import java.time.LocalDateTime;

public class ClientAddress {
    private boolean isDefault;
    private String city;
    private String street;
    private String houseNumber;
    private String apartment;
    private String postal_code;
    private LocalDateTime calcDeliveryTime;

    public ClientAddress(String city, String street, String houseNumber,
                         String apartment) {
        this.isDefault = true;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartment = apartment;
        //this.postal_code = postal_code;
        //this.calcDeliveryTime = calcDeliveryTime;
    }

    public String toString(){
        return city + ", " + street  + ", " + houseNumber + ", " + apartment + ".";
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getCalcDeliveryTime() {
        return calcDeliveryTime;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCalcDeliveryTime(LocalDateTime calcDeliveryTime) {
        this.calcDeliveryTime = calcDeliveryTime;
    }
}
