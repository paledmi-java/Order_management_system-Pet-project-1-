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
            if (!client.isAuthorised()){
                menuService.showMainMenuForUser();
            } else {
                menuService.showMainMenuForClient();
            }

            int firstOption = scanner.nextInt();

            switch (firstOption) {

                case 1:
                    boolean isExitFromFoodMenu = false;
                    do {
                        menuService.showWholefoodMenu();

                        int choseAnItemOption = scanner.nextInt();
                        scanner.nextLine();

                        MenuResult nextMenu = menuService.showSingleItem(client, choseAnItemOption);

                        if (nextMenu.isExit() && nextMenu.getItem() == null){
                            isExitFromFoodMenu = true;

                        } else if (!nextMenu.isExit() && nextMenu.getItem() != null){

                            menuService.putInBasketMessage();

                            int putItBasketOption = scanner.nextInt();
                            scanner.nextLine();

                            MenuService.PutInBasketResult putInBasketResult =
                                    menuService.getPutInCartResult(client, nextMenu.getItem(), putItBasketOption);

                            if (putInBasketResult == MenuService.PutInBasketResult.NEED_AUTH){
                                   Client clientAuthorised = menuService.startLoginProcess(client, scanner);
                                   if(clientAuthorised != null){
                                       client = clientAuthorised;
                                   }
                                   menuService.addItemInCart(client, nextMenu.getItem());
                                menuService.showSuccessPutInBasketMessage();
                            } else if (putInBasketResult == MenuService.PutInBasketResult.ADDED){
                                menuService.addItemInCart(client, nextMenu.getItem());
                                menuService.showSuccessPutInBasketMessage();
                                // NEED TO FINISH
                            } else if(putInBasketResult == MenuService.PutInBasketResult.BACK_TO_MENU){
                                isExitFromFoodMenu = true;
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

                }

                case 4: {
                    client = menuService.startLoginProcess(client, scanner);
                    break;
                }

                case 5: {
                    menuService.startRegistrationProcess(scanner);
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


