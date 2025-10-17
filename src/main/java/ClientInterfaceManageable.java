import java.time.LocalDate;

public interface ClientInterfaceManageable {
    void changeClientName(String newName);
    void changeDateOfBirth(LocalDate newDateOfBirth);
    void changePhoneNumber(String newPhoneNumber);
    void changeEmail(String newEmail);
    void changeAddress(ClientAddress changeableAddress);
    void turnOffAds();
    void profileIsComplete();
    void changeBonusesAmount(int newBonusesAmount);
    void addOrderInOrderHistory(Order order);
    void addNewFavouriteItem(Item item);
    void addNewClientAddress(ClientAddress newAddress);
}
