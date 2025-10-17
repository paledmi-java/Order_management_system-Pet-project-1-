import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class ClientsManagerSystem implements ClientsManageable {
    Client client = new Client();
    int clientIdCounter = 0;
    private final HashMap<Integer, Client> allClients = new HashMap<>();
    private final HashSet<String> allClientEmails = new HashSet<>();

    @Override
    public void addAClient(Client client) {
        clientIdCounter++;
        allClients.put(client.getCustomerId(), client);
        allClientEmails.add(client.getEmail());
    }

    @Override
    public void removeAClient(Client client) {
        allClients.remove(client.getCustomerId());
        allClientEmails.remove(client.getEmail());
    }
}
