import java.time.Duration;
import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private Client customer;
    private int price;
    private boolean isDeliveryFree;
    private ClientAddress orderAddress;
    private OrderStatus status;
    private String commentary;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private Duration estimatedDeliveryTime;
    private boolean areBonusesUsed;
    private boolean isPromoCodeUsed;

    public enum OrderStatus{
        ACTIVE,
        DECLINED,
        DELIVERED,
        UNPAID
    }

    public Order(int orderId, Client client, int price, String commentary,
                 boolean isDeliveryFree, ClientAddress orderAddress,
                 OrderStatus status, LocalDateTime createdAt,
                 Duration estimatedDeliveryTime, boolean areBonusesUsed,
                 boolean isPromoCodeUsed) {
        this.orderId = orderId;
        this.customer = client;
        this.price = price;
        this.commentary = commentary;
        this.isDeliveryFree = isDeliveryFree;
        this.orderAddress = orderAddress;
        this.status = status;
        this.commentary = "";
        this.createdAt = createdAt;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.areBonusesUsed = areBonusesUsed;
        this.isPromoCodeUsed = isPromoCodeUsed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", price=" + price +
                ", isDeliveryFree=" + isDeliveryFree +
                ", orderAddress=" + orderAddress +
                ", status=" + status +
                ", commentary='" + commentary + '\'' +
                ", createdAt=" + createdAt +
                ", deliveredAt=" + deliveredAt +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", areBonusesUsed=" + areBonusesUsed +
                ", isPromoCodeUsed=" + isPromoCodeUsed +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDeliveryFree() {
        return isDeliveryFree;
    }

    public void setDeliveryFree(boolean deliveryFree) {
        isDeliveryFree = deliveryFree;
    }

    public ClientAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(ClientAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isPromoCodeUsed() {
        return isPromoCodeUsed;
    }

    public void setPromoCodeUsed(boolean promoCodeUsed) {
        isPromoCodeUsed = promoCodeUsed;
    }

    public boolean isAreBonusesUsed() {
        return areBonusesUsed;
    }

    public void setAreBonusesUsed(boolean areBonusesUsed) {
        this.areBonusesUsed = areBonusesUsed;
    }

    public Duration getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Duration estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
