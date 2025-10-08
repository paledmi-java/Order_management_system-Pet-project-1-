import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public class CustomerAddress {
    private boolean isDefault;
    private String city;
    private String address;
    private LocalDateTime calcDeliveryTime;

    public CustomerAddress(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
