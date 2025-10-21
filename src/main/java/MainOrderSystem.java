import java.beans.Customizer;
import java.util.Random;
import java.util.Scanner;

public class MainOrderSystem {

    public static void main(String[] args){
        ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
        MenuController menuController = new MenuController();
        menuController.fillTheFoodMenu();

        Client client = new Client();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        do {
            menuController.showMainMenuForUser();
            int firstOption = scanner.nextInt();

            switch (firstOption) {

                case 1:
                    boolean isExitFromFoodMenu = false;
                    do {
                        menuController.showWholefoodMenu();
                        int choseAnItemOption = scanner.nextInt();
                        scanner.nextLine();

                        MenuResult nextMenu = menuController.showSingleItem(client, choseAnItemOption);

                        if (nextMenu.isExit() && nextMenu.getItem() == null){
                            isExitFromFoodMenu = true;

                        } else if (!nextMenu.isExit() && nextMenu.getItem() != null){

                            menuController.putInBasketMessage();
                            int putItBasketOption = scanner.nextInt();
                            scanner.nextLine();

                            MenuController.ActionResult putInBasketResult = menuController.getPutInCartResult(client, nextMenu.getItem(), putItBasketOption);
                            if (putInBasketResult == MenuController.ActionResult.NEED_AUTH){
                                menuController.startLoginProcess(client, scanner);
                                // ПОМЕНЯТЬ НАЗВАНИЯ МЕТОДОВ КЛАССОВ И ПЕРЕМЕННЫХ
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
            }
        } while (!isExit);
    }
}


