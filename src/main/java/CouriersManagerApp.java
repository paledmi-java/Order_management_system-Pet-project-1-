import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CouriersManagerApp {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        DeliveryManagerSystem deliveryManagerSystem = new DeliveryManagerSystem();
        int couriersCount = deliveryManagerSystem.createSomeCouriers();
        deliveryManagerSystem.setExecutorThreads();

        ClientsManagerSystem clientsManagerSystem = new ClientsManagerSystem();
        ProductsManagerSystem productsManagerSystem = new ProductsManagerSystem();
        OrdersManagerSystem ordersManagerSystem = new OrdersManagerSystem();

        clientsManagerSystem.createAndAddSomeClients();
        HashMap<Integer, Client> clients = clientsManagerSystem.getAllClients();

        deliveryManagerSystem.createSomeOrders(ordersManagerSystem, clients);
        productsManagerSystem.fillTheFoodMenu();


        do {
        System.out.println("\n0) Start delivery simulation\n" +
                            "1) Show all clients\n" +
                            "2) Show all couriers\n"+
                            "3) Add some orders\n" +
                            "4) Show all orders\n" +
                            "5) Stop delivery simulation");


        ArrayList<Courier> couriers = deliveryManagerSystem.getCouriers();
        String input = scanner.nextLine();
        int option = Integer.parseInt(input);

            if(option == 0){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        deliveryManagerSystem.startSimForDeliveryApp(ordersManagerSystem);
                    }
                }).start();

            } else if(option == 1){
                clientsManagerSystem.showAllClients();
            } else if (option == 2) {
                deliveryManagerSystem.showAllCouriers();
                String input2 = scanner.nextLine();
                int option2 = Integer.parseInt(input);
                if(option2 >= 1 && option2 < couriers.size()){
                    System.out.println(couriers.get(option - 1));
                }
            } else if (option == 3){
                deliveryManagerSystem.createSomeOrders(ordersManagerSystem, clients);
            } else if (option == 4){
                for(Order order : ordersManagerSystem.getOrdersBlockingQueue()){
                    System.out.println(order);
                }
            } else if (option == 5){
            deliveryManagerSystem.setRunning(false);
        }
            else {
                System.out.println("Please chose the right option.");
            }
        } while (true);

    }
}
