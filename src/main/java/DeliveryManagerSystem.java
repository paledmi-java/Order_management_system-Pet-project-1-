import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class DeliveryManagerSystem {
    private final ArrayList<Courier> couriers = new ArrayList<>();
    private static int couriersCount;
    private static int courierId = 1;

    ExecutorService couriersWorkExecutor;
    BlockingQueue<Courier> freeCourierBlockingQueue = new LinkedBlockingQueue<>();


    public void setCouriersCount(int count) {
        couriersCount = count;
        couriersWorkExecutor = Executors.newFixedThreadPool(couriersCount);
    }

    public void addACourier(String name, Courier.CourierStatus courierStatus, Courier.CourierType courierType){
        Courier courier = new Courier( courierId++,  name, courierStatus, courierType);
        couriersCount++;
        couriers.add(courier);
        freeCourierBlockingQueue.add(courier);
    }

    public void showAllCouriers(){
        for (int i = 0; i < couriers.size(); i++){
            System.out.println((i + 1) + ") " + couriers.get(i).getName() + " - " +
                    couriers.get(i).getCourierStatus());
        }
    }

    public synchronized Order startDeliverySimulation(Order order, OrdersManagerSystem ordersManagerSystem){
        Thread threadSearch = new Thread(new Runnable() {
            Courier courierFound;
            @Override
            public void run() {
                ArrayList<Courier> couriers = getCouriers();
                boolean courierIsFoud = false;
                do {
                    for (Courier courier : couriers){
                        System.out.println("Searching for couriers...");
                        if(courier.getCourierStatus() == Courier.CourierStatus.FREE){
                            courierIsFoud = true;
                            courierFound = courier;
                            System.out.println("Courier " + courierFound.getName() +
                                    " will deliver your order at " + order.getEstimatedDeliveryTime());
                            courierFound.setCourierStatus(Courier.CourierStatus.DELIVERING);
                            System.out.println(courierFound.getName() + " is delivering your order");
                            try {
                                Thread.sleep(60000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Your order is delivered");
                            courierFound.setCourierStatus(Courier.CourierStatus.FREE);
                            order.setDeliveredAt(LocalDateTime.now());
                            order.setStatus(Order.OrderStatus.DELIVERED);

                        } else {
                            System.out.println("Courier is not found yet. Please wait...");
                            try {
                                for(int i = 0; i<7; i++){
                                    System.out.println("wait...");
                                    Thread.sleep(1000);
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } while (!courierIsFoud);
            }
        }); threadSearch.start();
        return order;
    }


    public void createSomeOrders(OrdersManagerSystem ordersManagerSystem, HashMap<Integer, Client> clients) throws InterruptedException {
        Order[] orders = ordersManagerSystem.createSomeOrders(clients.get(0), clients.get(1), clients.get(2));
        for (Order order : orders) {
            ordersManagerSystem.getOrdersBlockingQueue().put(order);
        }
    }


    public Order searchAnOrder(OrdersManagerSystem ordersManagerSystem) {
        Order foundOrder = null;
        try {
            foundOrder = ordersManagerSystem.getOrdersBlockingQueue().take();
        } catch (InterruptedException e) {
//            System.out.println("There are no orders yet...");
            throw new RuntimeException(e);
        }
        return foundOrder;
    }


    public Courier searchForACourier (Order order){
        Courier foundCourier = null;
        try {
            foundCourier = freeCourierBlockingQueue.take();
        } catch (InterruptedException e) {
//            System.out.println("There are no free couriers yet...");
            throw new RuntimeException(e);
        }
        return foundCourier;
    }

// ИСПРАВИТЬ ПО ПОСЛЕДНЕМУ СООБЩЕНИЮ НЕЙРОНКИ
    public void startSimForDeliveryApp(OrdersManagerSystem ordersManagerSystem){
        while (true) {
            Order foundOrder = searchAnOrder(ordersManagerSystem);
            Courier foundCourier = searchForACourier(foundOrder);

            couriersWorkExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // ВЕРНО ЛИ???
                    System.out.println("Courier " + foundCourier.getName() +
                            " will deliver your order at " + foundOrder.getEstimatedDeliveryTime());
                    foundCourier.setCourierStatus(Courier.CourierStatus.DELIVERING);
                    System.out.println(foundCourier.getName() + " is delivering your order");
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Your order is delivered");
                    foundCourier.setCourierStatus(Courier.CourierStatus.FREE);
                    foundOrder.setDeliveredAt(LocalDateTime.now());
                    foundOrder.setStatus(Order.OrderStatus.DELIVERED);
                }
            });
        }
    }



    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public int getCouriersCount() {
        return couriersCount;
    }



    public int createSomeCouriers(){
        addACourier("Bob",
                Courier.CourierStatus.FREE, Courier.CourierType.ON_FOOT);
        addACourier("Mike",
                Courier.CourierStatus.FREE, Courier.CourierType.BIKE);
        addACourier("Nick",
                Courier.CourierStatus.FREE, Courier.CourierType.CAR);
//        addACourier("Dave",
//                Courier.CourierStatus.FREE, Courier.CourierType.ON_FOOT);
//        addACourier("Rick",
//                Courier.CourierStatus.FREE, Courier.CourierType.BIKE);
//        addACourier("Brian",
//                Courier.CourierStatus.FREE, Courier.CourierType.CAR);
        return 3;
    }


}

