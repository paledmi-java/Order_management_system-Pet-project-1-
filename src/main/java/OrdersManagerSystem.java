import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrdersManagerSystem implements OrdersManageable{

    private static int orderIDCounter = 0;
    // FOR FUTURE. POSSIBILITY OF WORKING IN OTHER CITIES
//    private final HashSet<String> cities = new HashSet<>();

    // FOR FUTURE. POSSIBILITY OF USING VIP STATUS CLIENT
//    private final PriorityQueue <Order> priorityOrders = new PriorityQueue<>();

    private final HashMap<String, Promo> promoCodes = new HashMap<>();
    private final TreeMap<Integer, Order> sortedOrdersById = new TreeMap<>();
    BlockingQueue<Order> orderBlockingQueue = new LinkedBlockingQueue<>();


    public Order createAnOrder(Client client,
                               int price, String comment,
                               ClientAddress orderAddress,
                               LocalDateTime deliveryTime,
                               boolean areBonusesUsed,
                               boolean isPromoCodeUsed){
        boolean isDeliveryFree;
        if(price > 1000){
            isDeliveryFree = true;
        } else {
            isDeliveryFree = false;
        }
        Duration duration = Duration.between(deliveryTime, LocalDateTime.now());

        Order order = new Order(orderIDCounter++,
                client, price, comment, isDeliveryFree,
                orderAddress, Order.OrderStatus.ACTIVE,
                LocalDateTime.now(), duration, areBonusesUsed, isPromoCodeUsed);

        sortedOrdersById.put(order.getOrderId(), order);
        orderBlockingQueue.add(order);
        client.getOrdersHistory().add(order);
        return order;
    }



    public BlockingQueue<Order> getOrdersBlockingQueue() {
        return orderBlockingQueue;
    }

    public HashMap<String, Promo> getPromoCodes() {
        return promoCodes;
    }

    public static int getOrderIDCounter() {
        return orderIDCounter;
    }

    public TreeMap<Integer, Order> getSortedOrdersById() {
        return sortedOrdersById;
    }


    // ИСПРАВИТЬ ВРЕМЯ ВЫВОДИТСЯ НЕПРАВИЛЬНО!
    public Order[] createSomeOrders(Client client1, Client client2, Client client3){
        String dateString1 = "2025-11-11T13:45:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateString1);

        Order order1 = createAnOrder(client1, 600, "gjgjgjg", client1.getDefaultAddress(),
                dateTime1, false, false);

        String dateString2 = "2025-11-11T09:45:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateString2);

        Order order2 = createAnOrder(client2, 700, "gjeeeegjg", client2.getDefaultAddress(),
                dateTime2, false, false);

        String dateString3 = "2025-11-11T10:45:00";
        LocalDateTime dateTime3 = LocalDateTime.parse(dateString3);

        Order order3 = createAnOrder(client3, 800, "darov", client3.getDefaultAddress(),
                dateTime3, false, false);

        String dateString4 = "2025-11-11T11:45:00";
        LocalDateTime dateTime4 = LocalDateTime.parse(dateString4);

        Order order4 = createAnOrder(client1, 900, "darov1", client1.getDefaultAddress(),
                dateTime4, false, false);

        String dateString5 = "2025-11-11T12:45:00";
        LocalDateTime dateTime5 = LocalDateTime.parse(dateString5);

        Order order5 = createAnOrder(client2, 1000, "darov2", client2.getDefaultAddress(),
                dateTime5, false, false);

        String dateString6 = "2025-11-11T14:45:00";
        LocalDateTime dateTime6 = LocalDateTime.parse(dateString6);

        Order order6 = createAnOrder(client3, 2000, "darovaaa", client3.getDefaultAddress(),
                dateTime6, false, false);

        Order[] orders = new Order[6];
        orders[0] = order1;
        orders[1] = order2;
        orders[2] = order3;
        orders[3] = order4;
        orders[4] = order5;
        orders[5] = order6;

        return orders;
    }


}