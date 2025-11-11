import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClientsManagerSystem implements ClientsManageable {
    private static int clientIdCounter = 0;
    private final HashMap<Integer, Client> allClients = new HashMap<>();
    private final HashSet<String> allClientEmails = new HashSet<>();
    private final HashMap<String, Credentials> allCredentials = new HashMap<>();

    public Client createAClient(String phoneNumber, String name,
                                String login, String password){
        Client client =  new Client (clientIdCounter++, phoneNumber, name, login, password);
        Credentials credentials = new Credentials(client.getLogin(), client.getPassword(), client.getClientId());
        client.setCredentials(credentials);
        return client;
    }

    @Override
    public void addAClient(Client client) {
        allClients.put(client.getClientId(), client);
        allClientEmails.add(client.getEmail());
        allCredentials.put(client.getLogin(), client.getCredentials());
    }

    @Override
    public void removeAClient(Client client) {
        allClients.remove(client.getClientId());
        allClientEmails.remove(client.getEmail());
    }

    public void getClientAddresses(){

    }

    public void showAllClients(){
        for(Client client : getAllClients().values()){
            System.out.println(client);
        }
    }

    public ArrayList<Item> getBasket(Client client){
        int i = 1;
        ArrayList<Item> basket = client.getBasket();
        for(Item item : basket){
            System.out.println(i + ") " + item);
            i++;
        }
        return basket;
    }

    public int getTotalBasketPrice(Client client){
        int i = 1;
        ArrayList<Item> basket = client.getBasket();
        int totalPrice = 0;
        for(Item item : basket){
            totalPrice += item.getPrice();
            i++;
        }
        System.out.println("Total price: " + totalPrice + "rub");
        return totalPrice;
    }



    public int getClientIdCounter() {
        return clientIdCounter;
    }

    public void setClientIdCounter(int clientIdCounter) {
        this.clientIdCounter = clientIdCounter;
    }

    public HashMap<Integer, Client> getAllClients() {
        return allClients;
    }

    public HashSet<String> getAllClientEmails() {
        return allClientEmails;
    }

    public HashMap<String, Credentials> getAllCredentials() {
        return allCredentials;
    }


    public void createAndAddSomeClients(){
        Client client1 = createAClient
                ("85948572480", "Paul", "paledmi", "123456" );
        addAClient(client1);

        ClientAddress clientAddress1 = new ClientAddress("Samara", "Alexey Tolstoy st.", "55", "10");
        ClientAddress clientAddress2 = new ClientAddress("Moscow", "Nevsky st.", "66", "4");
        ClientAddress clientAddress3 = new ClientAddress("Kazan", "Pushkin st.", "20", "8");

        client1.getClientAddresses().add(clientAddress1);
        client1.getClientAddresses().add(clientAddress2);
        client1.getClientAddresses().add(clientAddress3);

        Client client2 = createAClient
                ("99999999999", "Alex", "alebuale", "qwerty" );
        addAClient(client2);

        Client client3 = createAClient
                ("48394058678L", "Mary", "marbule", "zxcvbn" );
        addAClient(client3);
        System.out.println("Clients are added");
    }


}
