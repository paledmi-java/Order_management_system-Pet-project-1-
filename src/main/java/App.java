import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
        MenuService menuService = new MenuService();
        menuService.fillTheFoodMenu();
        menuService.createAndAddSomeClients();

        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        do {
//            assert client != null;
            if (!client.isAuthorised()){
                menuService.showMainMenuForUser();
            } else {
                menuService.showMainMenuForClient();
            }

            int firstOption = scanner.nextInt();
            scanner.nextLine();

            switch (firstOption) {

                case 1:
                    boolean isExitFromFoodMenu = false;
                    do {

                        Item chosenItem = menuService.showWholefoodMenu(client, scanner);

                        if (chosenItem == null){
                            isExitFromFoodMenu = true;

                        } else {
                            Client newClient = menuService.putInBasketMenu(client, chosenItem, scanner);
                            if (newClient != null){
                                client = newClient;
                            }
                        }
                    }  while (!isExitFromFoodMenu);
                    break;


                case 2:
                    menuService.startPromosScreen(scanner);
                    break;

                case 3: {
                    menuService.startSearchProcess(client, scanner);
                    break;
                }

                case 4: {
                    if(!client.isAuthorised()){
                        Client newClient = menuService.startLoginProcess(client, scanner);
                        if(newClient != null){
                            client = newClient;
                        } else {
                            break;
                        }
                    } else{
                        menuService.getBasketMenu(client, scanner);
                    }
                    break;
                }

                case 5: {
                    // CHOSE OPTIONS FOR AUTHORISED CLIENT IS REPEATING THE CODE
                    if(!client.isAuthorised()) {
                        Client newClient = menuService.startRegistrationProcess(scanner);
                        if(newClient != null){
                            client = newClient;
                        } else {
                            break;
                        }
                    } else {
                        client = menuService.startLogOutProcess(client);
                        System.out.println("You're successfully logged out.");
                    }
                    break;
                }

                case 6: {
                    if(!client.isAuthorised()){
                        menuService.getBasketMenu(client, scanner);
                    } else {
                        System.out.println("Please chose the right option\n");
                        break;
                    }
                    break;
                }

                case 0: {
                    isExit = true;
                    break;
                }

                default:{
                    System.out.println("Please chose the right option\n");
                    break;
                }

            }
        } while (!isExit);
    }
}


