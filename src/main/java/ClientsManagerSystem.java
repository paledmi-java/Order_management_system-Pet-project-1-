import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class ClientsManagerSystem implements ClientsManageable {
    int clientIdCounter = 0;
    private final HashMap<Integer, Client> allClients = new HashMap<>();
    private final HashSet<String> allClientEmails = new HashSet<>();
    private final HashMap<String, Credentials> allCredentials = new HashMap<>();

    public Client createAClient(String phoneNumber, String name,
                                String login, String password){
        Client client =  new Client(clientIdCounter++, phoneNumber, name, login, password);
        Credentials credentials = new Credentials(client.getLogin(), client.getPassword(), client.getClientId());
        client.setCredentials(credentials);
        return client;
    }

    @Override
    public void addAClient(Client client) {
        clientIdCounter++;
        allClients.put(client.getClientId(), client);
        allClientEmails.add(client.getEmail());
        allCredentials.put(client.getLogin(), client.getCredentials());
    }

    @Override
    public void removeAClient(Client client) {
        allClients.remove(client.getClientId());
        allClientEmails.remove(client.getEmail());
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
}
