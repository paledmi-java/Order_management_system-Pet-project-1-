import java.util.Random;
import java.util.Scanner;

public class MenuController {
    private final Random random = new Random();
    private final ProductsManagerSystem productsManagerSystem = new ProductsManagerSystem();
    private final ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
//    private final Scanner scanner = new Scanner(System.in);

    public void showMainMenuForUser(){
        System.out.println("Chose option number:\n" +
                "1) Menu\n" +
                "2) Promotions\n" +
                "3) Search\n" +
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

    public enum ActionResult{
        ADDED,
        NEED_AUTH,
        BACK_TO_MENU
    }

    public void putInBasketMessage() {
        System.out.println("Put this Item in your basket?\n" +
                "1) Yes\n" +
                "2) No");
    }

    public ActionResult getPutInCartResult(Client client, Item theItem, int lastOption){
        if(lastOption == 1 && client.isAuthorised()){
            client.getBucket().add(theItem);
            System.out.println("The item added to your kart");
            return ActionResult.ADDED;
        } else if (lastOption == 1 && !client.isAuthorised()){
            System.out.println("Please log in or sign up first");
            return ActionResult.NEED_AUTH;
        } else if (lastOption == 2){
            return ActionResult.BACK_TO_MENU;
        } else {
            System.out.println("Please chose the right number");
            return null;
        }
    }

    public enum AuthorisationResult{
        NEED_REGISTRATION,
        WRONG_PASSWORD,
        AUTH_SUCCESS
    }

    public AuthorisationResult authorization(String login, String password){
        Credentials credentials = clientsManagerSystem.getAllCredentials().get(login);
        if(credentials == null){
            return AuthorisationResult.NEED_REGISTRATION;
        } else if (credentials.getHashedPassword().equals(password)){
            return AuthorisationResult.AUTH_SUCCESS;
        } else return AuthorisationResult.WRONG_PASSWORD;
    }


    // IS NOT READY! IMPROVE!
    public void startLoginProcess(Client client, Scanner scanner){
        boolean isExitFromLoginMenu = false;
        do {
            System.out.println("Please enter your login\n" +
                    "Login: ");
            String login = scanner.nextLine();
            System.out.println("Please enter your password\n" +
                    "Password: ");
            String password = scanner.nextLine();

            MenuController.AuthorisationResult authResult = this.authorization(login, password);
            if (authResult == MenuController.AuthorisationResult.WRONG_PASSWORD) {
                System.out.println("Wrong password. Press '1' to Try Again or '0' Exit");
                int authOrExit = scanner.nextInt();
                scanner.nextLine();
                switch (authOrExit){
                    case 1 -> isExitFromLoginMenu = true;
                    case 2 -> {}
                    default -> System.out.println("Please chose the right option\n");
                }
            } else if (authResult == MenuController.AuthorisationResult.AUTH_SUCCESS){
                isExitFromLoginMenu = true;
            } else if(authResult == AuthorisationResult.NEED_REGISTRATION){
                startRegistrationProcess();
            }

        } while (!isExitFromLoginMenu);
    }

    public void startRegistrationProcess(){

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
}
