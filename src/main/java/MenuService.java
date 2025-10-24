import java.util.Random;
import java.util.Scanner;

public class MenuService {
    private final Random random = new Random();
    private final ProductsManagerSystem productsManagerSystem = new ProductsManagerSystem();
    private final ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
//    private final Scanner scanner = new Scanner(System.in);

    public void showMainMenuForUser(){
        System.out.println("Chose option number:\n" +
                "1) Menu\n" +
                "2) Promotions\n" +
                "3) Search\n" + //IS NOT WORKING
                "4) Log in\n" +
                "5) Sign up\n" +
                "6) Show my basket\n" +
                "7) Exit");
    }

    public void showMainMenuForClient(){
        System.out.println("Chose option number:\n" +
                "1) Menu\n" +
                "2) Promotions\n" +
                "3) Search\n" +
                "4) Show my basket\n" +
                "5) Exit");
    }

    public void showWholefoodMenu() {
        System.out.println("Chose a number of Item or 0 to go back");
        productsManagerSystem.getFullMenu();
        System.out.println();
    }

    public MenuResult showSingleItem(Client client, int showAnItemOption){
            if (showAnItemOption > 0 && showAnItemOption < productsManagerSystem.getAllItemsList().size()) {
                Item theItem = productsManagerSystem.getAllItemsList().get(showAnItemOption);
                System.out.println(theItem.itemFormat(Item.itemFormatType.PUBLIC) + "\n");
                return new MenuResult(theItem, false);
            } else if (showAnItemOption == 0) {
                return new MenuResult(null, true);
            } else {
                System.out.println("Please chose the right number");
                return new MenuResult(null, false);
            }
    }

    public void startSearchProcess(Scanner scanner){
        System.out.println("Enter name of the item: ");
        String itemName = scanner.nextLine();
        Item searchItem = productsManagerSystem.getSearchOfItemList().get(itemName);
        if( searchItem != null){
            System.out.println(searchItem.itemFormat(Item.itemFormatType.PUBLIC));
            putInBasketMessage();
        } else {
            System.out.println("The item is not found. Try again or type 0 to exit.");
        }
    }




    public enum PutInBasketResult {
        ADDED,
        NEED_AUTH,
        BACK_TO_MENU
    }

    public void putInBasketMessage() {
        System.out.println("Put this Item in your basket?\n" +
                "1) Yes\n" +
                "2) No");
    }

    public void showSuccessPutInBasketMessage(){
        System.out.println("The item successfully added to your cart");
    }

    public void addItemInCart(Client client, Item theItem){
        client.getBucket().add(theItem);
//        System.out.println("The item added to your kart");
    }
    public PutInBasketResult getPutInCartResult(Client client, Item theItem, int lastOption){
        if(lastOption == 1 && client.isAuthorised()){
            addItemInCart(client, theItem);
            return PutInBasketResult.ADDED;
        } else if (lastOption == 1 && !client.isAuthorised()){
            System.out.println("Please log in or sign up first");
            return PutInBasketResult.NEED_AUTH;
        } else if (lastOption == 2){
            return PutInBasketResult.BACK_TO_MENU;
        } else {
            System.out.println("Please chose the right number");
            return null;
        }
    }

    public enum AuthorisationResultEnum {
        NEED_REGISTRATION,
        WRONG_PASSWORD,
        AUTH_SUCCESS
    }

    public AuthorisationResult authorizationGetResult(String login, String password){
        Credentials credentials = clientsManagerSystem.getAllCredentials().get(login);
        if(credentials == null){
            return new AuthorisationResult(AuthorisationResultEnum.NEED_REGISTRATION, null);
        } else if (credentials.getHashedPassword().equals(password) && credentials.getLogin().equals(login)){
            return new AuthorisationResult(AuthorisationResultEnum.AUTH_SUCCESS, credentials);
        } else return new AuthorisationResult(AuthorisationResultEnum.WRONG_PASSWORD, null);
    }


    public Credentials showLoginMessage(Scanner scanner){
        System.out.println("Please enter your login\n" +
                "Login: ");
        String login = scanner.nextLine();
        System.out.println("Please enter your password\n" +
                "Password: ");
        String password = scanner.nextLine();
        return new Credentials(login, password);
    }


    public Client startLoginProcess(Client client, Scanner scanner){
        boolean isExitFromLoginMenu = false;
        do {
            Credentials credentialsToAuth = showLoginMessage(scanner);

            AuthorisationResult authResult = this.authorizationGetResult(credentialsToAuth.getLogin(), credentialsToAuth.getHashedPassword());
            if (authResult.getAuthorisationResultEnum() == AuthorisationResultEnum.WRONG_PASSWORD) {
                System.out.println("Wrong password. Press '1' to Try Again or '0' Exit");
                int authOrExit = scanner.nextInt();
                scanner.nextLine();
                switch (authOrExit){
                    case 0 -> isExitFromLoginMenu = true;
                    case 1 -> {}
                    default -> System.out.println("Please chose the right option\n");
                }
                client = null;
            } else if (authResult.getAuthorisationResultEnum() == AuthorisationResultEnum.AUTH_SUCCESS){
                client = this.clientsManagerSystem.getAllClients().get(authResult.getCredentials().getClientId());
                System.out.println("Authorisation success");
                isExitFromLoginMenu = true;
            } else if(authResult.getAuthorisationResultEnum() == AuthorisationResultEnum.NEED_REGISTRATION){

                System.out.println("You're not in system, please sign up first\n" +
                        "Press 1 to sign up or 0 to exit");

                int signUpOption = scanner.nextInt();
                scanner.nextLine();
                boolean signUpExit = false;
                do{
                if(signUpOption == 0){
                    client = null;
                    signUpExit = true;
                    isExitFromLoginMenu = true;
                } else if (signUpOption == 1){
                    client = startRegistrationProcess(scanner);
                    signUpExit = true;
                    isExitFromLoginMenu = true;
                } else System.out.println("Please chose the right option\n");
                } while (!signUpExit);
            }
        } while (!isExitFromLoginMenu);
        return client;
    }


    public Client startRegistrationProcess(Scanner scanner){
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your phone: ");
        String phone = scanner.nextLine();
        Credentials credsOfNewClient = showLoginMessage(scanner);
        Client newClient = clientsManagerSystem.createAClient(phone,name,credsOfNewClient.getLogin(), credsOfNewClient.getHashedPassword());
        clientsManagerSystem.addAClient(newClient);
        System.out.println("You successfully signed up");
        return newClient;
    }


    public void fillTheFoodMenu(){
        Item item1 = productsManagerSystem.createAnItem("Roll Dragon", "Roll",
                "Rice, Dragon", 8, 593,
                "Roll made out of Dragon", 230, 237,
                String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true );

        productsManagerSystem.addAnItemToSystem(item1);

        Item item2 = productsManagerSystem.createAnItem("Tempura Roll with eel",
                "Tempura Roll", "Tempura, Rice, Eel", 8,
                447, "Tempura Roll made out of eel", 330, 455,
                String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true);

        productsManagerSystem.addAnItemToSystem(item2);

        Item item3 = productsManagerSystem.createAnItem("Salmon Roll with spicy mango sauce",
                "Baked roll", "Salmon, Rice, Spicy sauce",
                8, 556, "Baked Salmon Roll with spicy mango sauce",
                456, 677,  String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true);

        productsManagerSystem.addAnItemToSystem(item3);
    }

    public void createAndAddSomeClients(){
       Client client1 = clientsManagerSystem.createAClient("85948572480", "Paul", "paledmi", "123456" );
       clientsManagerSystem.addAClient(client1);

        Client client2 = clientsManagerSystem.createAClient("99999999999", "Alex", "alebuale", "qwerty" );
        clientsManagerSystem.addAClient(client1);

        Client client3 = clientsManagerSystem.createAClient("48394058678L", "Mary", "marbule", "zxcvbn" );
        clientsManagerSystem.addAClient(client1);
    }
}
