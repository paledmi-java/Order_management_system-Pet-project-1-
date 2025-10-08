import java.util.*;

public class OrderSystem implements ManageableOrders{
    private ArrayList<Item> allItems;
    private HashMap<Integer, Customer> allCustomers;
    private HashSet<String> allEmails;
    private HashSet<String> searchOfItem;
    private TreeMap<Integer, Order> sortedOrdersById;
    private TreeSet<Item> sortedItemsByPrice;
    private PriorityQueue <Order> priorityOrders;
    private ArrayDeque<Order> ordersMainQueue;


}