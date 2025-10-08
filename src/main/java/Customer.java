import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Customer {
    private int customerId;
    boolean isActive;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private CustomerAddress address;
    boolean isAdvertisable;
    boolean isProfileComplete;
    boolean isOnlineCheckOn;
    private int bonusesAmount;
    private LinkedHashSet<Order> ordersHistory;
    private LinkedHashSet<Item> favouriteItems;
    private ArrayList<CustomerAddress> addresses;

    public Customer(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        boolean isProfileComplete = false;
        this.isActive = true;
        this.isAdvertisable = true;
        this.isOnlineCheckOn = true;
        this.bonusesAmount = 0;
//        ordersHistory = new LinkedHashSet<>();
//        favouriteItems = new LinkedHashSet<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public boolean isAdvertisable() {
        return isAdvertisable;
    }

    public boolean isProfileComplete() {
        return isProfileComplete;
    }

    public boolean isOnlineCheckOn() {
        return isOnlineCheckOn;
    }

    public int getBonusesAmount() {
        return bonusesAmount;
    }

    public LinkedHashSet<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public LinkedHashSet<Item> getFavouriteItems() {
        return favouriteItems;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    public void setAdvertisable(boolean advertisable) {
        isAdvertisable = advertisable;
    }

    public void setProfileComplete(boolean profileComplete) {
        isProfileComplete = profileComplete;
    }

    public void setOnlineCheckOn(boolean onlineCheckOn) {
        isOnlineCheckOn = onlineCheckOn;
    }

    public void setBonusesAmount(int bonusesAmount) {
        this.bonusesAmount = bonusesAmount;
    }
}
