import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MenuService {
    private Random random;
    private ProductsManagerSystem productsManagerSystem;
    private ClientsManagerSystem clientsManagerSystem;
    private DeliveryManagerSystem deliveryManagerSystem;
    private OrdersManagerSystem ordersManagerSystem;

    public MenuService(Random random, ProductsManagerSystem productsManagerSystem,
                       ClientsManagerSystem clientsManagerSystem,
                       DeliveryManagerSystem deliveryManagerSystem,
                       OrdersManagerSystem ordersManagerSystem) {

        this.random = random;
        this.productsManagerSystem = productsManagerSystem;
        this.clientsManagerSystem = clientsManagerSystem;
        this.deliveryManagerSystem = deliveryManagerSystem;
        this.ordersManagerSystem = ordersManagerSystem;
    }

    public void showMainMenuForUser(){
        System.out.println("Chose option number:\n" +
                "1) Menu\n" +
                "2) Promotions\n" +
                "3) Search\n" +
                "4) Log in\n" +
                "5) Sign up\n" +
                "6) Show my basket\n" +
                "0) Exit");
    }

    public void showMainMenuForClient(){
        System.out.println("Chose option number:\n" +
                "1) Menu\n" +
                "2) Promotions\n" +
                "3) Search\n" +
                "4) Show my basket\n" +
                "5) Log out\n" +
                "6) Exit\n");
    }

    public void startPromosScreen(Scanner scanner){
        boolean isExitFromPromos = false;
        do {
            System.out.println("Chose a promo:\n" +
                    "1) Discount\n" +
                    "2) Bigger discount\n" +
                    "3) The biggest discount!\n" +
                    "0) Exit");
            int promoOption = scanner.nextInt();
            switch (promoOption) {
                case 1 -> System.out.print("BUY OUR STUFF WITH A DISCOUNT OF 0.001%!!!\n");
                case 2 -> System.out.print("BUY OUR STUFF WITH A DISCOUNT OF 0.002%!!!\n");
                case 3 -> System.out.print("BUY OUR STUFF WITH A DISCOUNT OF 0.003%!!!\n");
                case 0 -> isExitFromPromos = true;
                default -> System.out.println("Please chose the right option\n");
            }
        } while (!isExitFromPromos);
    }

    // FINISH GET BASKET MENU
    public void getBasketMenu(Client client, Scanner scanner,
                              DeliveryManagerSystem deliveryManagerSystem, OrdersManagerSystem ordersManagerSystem){
        boolean isBasketExit = false;
        do {
            if(!clientsManagerSystem.getBasket(client).isEmpty()) {
                int totalPrice = clientsManagerSystem.getTotalBasketPrice(client);
                // NEED THIS MESSAGE TO PRINT BEFORE BASKET CONTAINS
                System.out.println("Chose a number of item to remove, 0 to continue purchase");
                int basketOption = scanner.nextInt();
                scanner.nextLine();
                if (basketOption > 0 && basketOption <= client.getBasket().size()) {
                    client.getBasket().remove(basketOption - 1);
                } else if (basketOption == 0) {
                    this.placingAnOrder(client, totalPrice, scanner, deliveryManagerSystem, ordersManagerSystem);
                }
                    System.out.println("Please chose the right option");
            } else {
                System.out.println("Your basket is empty, please add Items first.");
                isBasketExit = true;
            }
        } while (!isBasketExit);
    }

    public ClientAddress addNewAddressProcess(Client client, Scanner scanner){
        boolean isExit = false;
        ClientAddress clientNewAddress;
        do {
            System.out.println("Please type an address for your delivery:");
            System.out.println("Enter name of your city: ");
            String city = scanner.nextLine();
            System.out.println("Enter name of the street: ");
            String street = scanner.nextLine();
            System.out.println("Enter your house number: ");
            String houseNumber = scanner.nextLine();
            System.out.println("Enter number of your apartment: ");
            String apartment = scanner.nextLine();
            clientNewAddress = new ClientAddress(city, street, houseNumber, apartment);
            ArrayList<ClientAddress> addresses = client.getClientAddresses();
            if (addresses.contains(clientNewAddress)) {
                System.out.println("This address already exists. Press enter to add a new address or type 0 to go back");
                String option = scanner.nextLine();
                    if (option.equals("0")){
                       isExit = true;
                    }
            }else{
                addresses.add(clientNewAddress);
                client.setDefaultAddress(clientNewAddress);
                System.out.println("The address is successfully added.");
                isExit = true;
            }
        } while (!isExit);
        return clientNewAddress;
    }

    public ClientAddress choseAnAddressProcess(Client client, Scanner scanner) {
        boolean isExit = false;
        ClientAddress chosenAddress;
        ArrayList<ClientAddress> addresses = client.getClientAddresses();
        do {
            if (addresses.isEmpty()) {
                System.out.println("You don't have delivery addresses, please add one.");
                chosenAddress = addNewAddressProcess(client, scanner);
            } else {
                System.out.println("Please chose an address or 0 to add a new address:");
                System.out.println(addresses);
                int addressOption = scanner.nextInt();
                scanner.nextLine();
                if (addressOption > 0 && addressOption <= addresses.size() - 1) {
                    chosenAddress = addresses.get(addressOption);
                    isExit = true;
                } else if (addressOption == 0) {
                    chosenAddress = addNewAddressProcess(client, scanner);
                    isExit = true;
                } else {
                    System.out.println("Please, chose the right option");
                    chosenAddress = null;
                }
            }
        } while (!isExit);
        return chosenAddress;
    }

    public LocalDateTime choseDeliveryTimeProcess(Scanner scanner){
        System.out.println("Please chose delivery time:\n" +
                "1) The Fastest (1 hour)\n" +
                "2) To a certain time");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1){
            return LocalDateTime.now().plusHours(1);

        } else if(option == 0){
            System.out.println("Please enter time for today delivery in format HH:mm:");
            String inputTime = scanner.nextLine();

            LocalTime chosenTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));
            return LocalDateTime.of(LocalDate.now(), chosenTime);
        } else {
            System.out.println("Please chose the right option.");
            return null;
        }
    }

    public boolean showBonusesMessage(Client client, Scanner scanner){
        boolean isExit = false;
        boolean areBonusesUsed;
        do {
            if (client.getBonusesAmount() != 0) {
                System.out.println("Would you like to use your bonuses? 1 bonus = 1 ruble.\n" +
                        "You have " + client.getBonusesAmount() + " bonuses." +
                        "1) Yes\n" +
                        "2) No");
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    areBonusesUsed = true;
                    isExit = true;
                } else if (option == 0) {
                    areBonusesUsed = false;
                    isExit = true;
                } else {
                    System.out.println("Please chose the right option.");
                    areBonusesUsed = false;
                }
            } else {
                areBonusesUsed = false;
            }
        } while (!isExit);
        return areBonusesUsed;
    }

    public String startCommentaryMessage(Scanner scanner){
        System.out.println("Please enter your commentary: ");
        return scanner.nextLine();
    }




    public boolean startPromoCodeMessage(Scanner scanner, OrdersManagerSystem ordersManagerSystem){
        boolean isExit = false;
        boolean isPromoCodeUsed;
        do {
            System.out.println("Enter promo code, or type 0 if you don't have one:");
            String promoCode = scanner.nextLine();

            if(promoCode.equals("0")){
                isExit = true;
                isPromoCodeUsed = false;
            } else if(ordersManagerSystem.getPromoCodes().containsKey(promoCode)){
                System.out.println("Promo code is applied!");
                isPromoCodeUsed = true;
                isExit = true;
            } else {
                System.out.println("Promo code doesn't exist");
                isPromoCodeUsed = false;
            }
        } while (!isExit);

        return isPromoCodeUsed;
    }

    public void placingAnOrder(Client client, int totalPrice, Scanner scanner,
                               DeliveryManagerSystem deliveryManagerSystem, OrdersManagerSystem ordersManagerSystem){
        System.out.println("Placing an order\n");
        ClientAddress address = choseAnAddressProcess(client, scanner);
        LocalDateTime chosenTime = choseDeliveryTimeProcess(scanner);
        boolean areBonusesUsed = showBonusesMessage(client, scanner);
        boolean isPromoCodeUsed = startPromoCodeMessage(scanner, ordersManagerSystem);
        String commentary = startCommentaryMessage(scanner);
        Order newOrder = ordersManagerSystem.createAnOrder(client, totalPrice, commentary, address,
                chosenTime, areBonusesUsed, isPromoCodeUsed);

        // ПОДСТАВИТЬ НОВУЮ РЕАЛИЗАЦИЮ СИМУЛЯЦИИ
//        Order orderFinished = deliveryManagerSystem.startDeliverySimulation(newOrder, ordersManagerSystem);
//        client.getOrdersHistory().add(orderFinished);
    }

    // Не создавать новые потоки внутри каждого вызова.
    //Пусть у тебя будет один общий пул потоков (через ExecutorService), где будут работать курьеры.
    //
    //Сделай коллекции потокобезопасными:
    //
    //Очередь заказов → BlockingQueue<Order>
    //
    //Курьеры → CopyOnWriteArrayList<Courier> или ConcurrentHashMap<Integer, Courier>
    //
    //Назначение курьера и изменение статусов — синхронизировать,
    //либо использовать synchronized, либо ReentrantLock вокруг этих операций.



    public Item showWholefoodMenu(Client client, Scanner scanner) {
        boolean isExitFromFoodMenu = false;
        Item theItem;
        do {
            System.out.println("Chose a number of Item or 0 to go back");
            productsManagerSystem.getFullMenu();
            System.out.println();

            int choseAnItemOption = scanner.nextInt();
            scanner.nextLine();

            if (choseAnItemOption > 0 && choseAnItemOption <= productsManagerSystem.getAllItemsList().size()) {
                theItem = productsManagerSystem.getAllItemsList().get(choseAnItemOption - 1);
                System.out.println(theItem.itemFormat(Item.itemFormatType.PUBLIC) + "\n");
                isExitFromFoodMenu = true;

            } else if (choseAnItemOption == 0) {
                theItem = null;
                isExitFromFoodMenu = true;

            } else {
                System.out.println("Please chose the right option");
                theItem = null;
            }
        } while (!isExitFromFoodMenu);
        return theItem;
    }

    public Client putInBasketMenu(Client client, Item theItem, Scanner scanner) {
        Client newClient;
        System.out.println("Put this Item in your basket?\n" +
                "1) Yes\n" +
                "2) No");
        int choseAnItemOption = scanner.nextInt();
        scanner.nextLine();
        if(choseAnItemOption == 1 && client.isAuthorised()){
            this.addItemInCart(client, theItem);
            newClient = null;

        } else if (choseAnItemOption == 1 && !client.isAuthorised()){
            System.out.println("Please log in or sign up first");
            newClient = this.startLoginProcess(client, scanner);
            if(newClient != null){
                this.addItemInCart(newClient, theItem);
            }
        } else if (choseAnItemOption == 2){
            newClient = null;
        } else {
            System.out.println("Please chose the right number");
            newClient = null;
        }
        return newClient;
    }

    public void startSearchProcess(Client client, Scanner scanner){
        boolean isExitFromSearch = false;
        do {
            System.out.println("Enter name of the item or 0 to go back: ");
            String itemName = scanner.nextLine();
            if (!itemName.equals("0")){
                Item searchItem = productsManagerSystem.getSearchOfItemList().get(itemName);
                if (searchItem != null) {
                    System.out.println(searchItem.itemFormat(Item.itemFormatType.PUBLIC));
                    putInBasketMenu(client, searchItem, scanner);
                } else {
                    System.out.println("The item is not found.");
                }
            } else{
                isExitFromSearch = true;
            }
        } while (!isExitFromSearch);
    }

    public enum PutInBasketResult {
        ADDED,
        NEED_AUTH,
        BACK_TO_MENU
    }

//    public void showSuccessPutInBasketMessage(){
//        System.out.println("The item successfully added to your cart");
//    }

    public void addItemInCart(Client client, Item theItem){
        client.getBasket().add(theItem);
        System.out.println("The item added to your kart");
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

            AuthorisationResult authResult = this.authorizationGetResult
                    (credentialsToAuth.getLogin(), credentialsToAuth.getHashedPassword());
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

                // IF 0 THEN THROWS NULL POINT EXCEPTION!!! BECAUSE NULL IS RETURNING!
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

    public Client startLogOutProcess(Client client){
        client = new Client();
        return client;
    }

    public Client startRegistrationProcess(Scanner scanner){
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your phone: ");
        String phone = scanner.nextLine();
        Credentials credOfNewClient = showLoginMessage(scanner);
        Client newClient = clientsManagerSystem.createAClient
                (phone,name, credOfNewClient.getLogin(), credOfNewClient.getHashedPassword());
        clientsManagerSystem.addAClient(newClient);
        System.out.println("You successfully signed up");
        return newClient;
    }

}
