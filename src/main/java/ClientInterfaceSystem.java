import java.time.LocalDate;

public class ClientInterfaceSystem implements ClientInterfaceManageable{
    Client client;

    @Override
    public void changeClientName(String newName) {
        client.setName(newName);
    }

    @Override
    public void changeDateOfBirth(LocalDate newDateOfBirth) {
        client.setDateOfBirth(newDateOfBirth);
    }

    @Override
    public void changePhoneNumber(String newPhoneNumber) {
        client.setPhoneNumber(newPhoneNumber);
    }

    @Override
    public void changeEmail(String newEmail) {
        client.setEmail(newEmail);
    }

    @Override
    public void changeAddress(ClientAddress changeableAddress) {
        client.setDefaultAddress(changeableAddress);
    }

    @Override
    public void turnOffAds() {
        client.setAdvertisable(false);
    }

    @Override
    public void profileIsComplete() {
        client.setProfileComplete(true);
    }

    @Override
    public void changeBonusesAmount(int newBonusesAmount) {
        client.setBonusesAmount(newBonusesAmount);
    }

    @Override
    public void addOrderInOrderHistory(Order order) {
        client.getOrdersHistory().add(order);
    }

    @Override
    public void addNewFavouriteItem(Item item) {
        client.getFavouriteItems().add(item);
    }

    @Override
    public void addNewClientAddress(ClientAddress newAddress) {
        client.getAddresses().add(newAddress);
    }
}
