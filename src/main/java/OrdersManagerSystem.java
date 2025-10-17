import java.util.*;

public class OrdersManagerSystem implements OrdersManageable{

    private final TreeMap<Integer, Order> sortedOrdersById = new TreeMap<>();
    private final PriorityQueue <Order> priorityOrders = new PriorityQueue<>();
    private final ArrayDeque<Order> ordersMainQueue = new ArrayDeque<>();

}