import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class DeliveryManagerSystem {
    private final ArrayList<Courier> couriers = new ArrayList<>();
    private static int courierId = 1;
    private volatile boolean isRunning = true;

    ScheduledExecutorService couriersWorkExecutor;
    BlockingQueue<Courier> freeCourierBlockingQueue = new LinkedBlockingQueue<>();


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setExecutorThreads() {
        couriersWorkExecutor = Executors.newScheduledThreadPool(couriers.size());
    }

    public void addACourier(String name, Courier.CourierStatus courierStatus, Courier.CourierType courierType){
        Courier courier = new Courier( courierId++,  name, courierStatus, courierType);
        couriers.add(courier);
        freeCourierBlockingQueue.add(courier);
    }

    public void showAllCouriers(){
        for (int i = 0; i < couriers.size(); i++){
            System.out.println((i + 1) + ") " + couriers.get(i).getName() + " - " +
                    couriers.get(i).getCourierStatus());
        }
    }


    public void createSomeOrders(OrdersManagerSystem ordersManagerSystem, HashMap<Integer, Client> clients) throws InterruptedException {
        ordersManagerSystem.createSomeOrders(clients.get(0), clients.get(1), clients.get(2));
    }


    public Order searchAnOrder(OrdersManagerSystem ordersManagerSystem) {
        Order foundOrder;
        try {
            foundOrder = ordersManagerSystem.getOrdersBlockingQueue().poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
//            System.out.println("There are no orders yet...");
            throw new RuntimeException(e);
        }
        return foundOrder;
    }


    public Courier searchForACourier (Order order){
        Courier foundCourier = null;
        while (foundCourier == null) {
            try {
                foundCourier = freeCourierBlockingQueue.take();
            } catch (InterruptedException e) {
    //            System.out.println("There are no free couriers yet...");
                throw new RuntimeException(e);
            }
        }
        return foundCourier;
    }

    public void startSimForDeliveryApp(OrdersManagerSystem ordersManagerSystem){
        long delayInSeconds = ThreadLocalRandom.current().nextLong(5, 21);
        if (delayInSeconds < 1){
            delayInSeconds = 1;
        }

        while (isRunning) {
            final Order foundOrder = searchAnOrder(ordersManagerSystem);
            if (foundOrder != null) {
                final Courier foundCourier = searchForACourier(foundOrder);
//                long delay = foundOrder.getEstimatedDeliveryTime().toMillis()/10;
                couriersWorkExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Courier " + foundCourier.getName() +
                                " will deliver " + foundOrder.getCustomer() + " order at " + foundOrder.getEstimatedDeliveryTime());
                        foundCourier.setCourierStatus(Courier.CourierStatus.DELIVERING);
                        System.out.println(foundCourier.getName() + " is delivering " + foundOrder.getCustomer() + "order");

                        try {
                            Thread.sleep(8000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println(foundOrder.getCustomer() + " order is delivered ");
                        foundCourier.setCourierStatus(Courier.CourierStatus.FREE);
                        foundOrder.setDeliveredAt(LocalDateTime.now());
                        foundOrder.setStatus(Order.OrderStatus.DELIVERED);
                        freeCourierBlockingQueue.add(foundCourier);
                        foundOrder.getCustomer().getOrdersHistory().add(foundOrder);
                    }
                }, delayInSeconds, TimeUnit.SECONDS);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        couriersWorkExecutor.shutdown();
    }

    public void stopDeliverySim(){
        isRunning = false;
        couriersWorkExecutor.shutdownNow();
        System.out.println("Delivery simulation stopped");
    }



    public ArrayList<Courier> getCouriers() {
        return couriers;
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

