import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
        MenuService menuService = new MenuService();
        menuService.fillTheFoodMenu();
        menuService.createAndAddSomeClients();

        Client client = new Client();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        do {
            assert client != null;
            if (!client.isAuthorised()){
                menuService.showMainMenuForUser();
            } else {
                menuService.showMainMenuForClient(); // MAKE CHOSE OPTIONS FOR AUTHORISED CLIENT
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
                            if (newClient == null){
                                isExitFromFoodMenu = true;
                            } else{
                                client = newClient;
                            }
                        }
                    }  while (!isExitFromFoodMenu);
                    break;


                case 2:
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


                case 3: {

                    break;
                }

                case 4: {
                    client = menuService.startLoginProcess(client, scanner);
                    break;
                }

                case 5: {
                    client = menuService.startRegistrationProcess(scanner);
                    break;
                }

//                case 6: {
//                    if(){
//
//                    }
//                }

            }
        } while (!isExit);
    }
}


