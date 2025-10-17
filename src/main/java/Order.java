import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private Client customer;
    private int price;
    private ArrayList<Item> bucket;
    private boolean isDeliverable;
    private boolean isDeliveryFree;
    private ClientAddress orderAddress;
    private String status;
    private String commentary;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime requiredDeliveryTime;
    private boolean isBonusUsed;
    private boolean isPromoCodeUsed;

    public Order(int orderId, Client customer, int price, ArrayList<Item> bucket,
                 boolean isDeliveryFree, boolean isDeliverable, ClientAddress orderAddress,
                 String status, LocalDateTime createdAt,
                 LocalDateTime requiredDeliveryTime, boolean isBonusUsed,
                 boolean isPromoCodeUsed) {
        this.orderId = orderId;
        this.customer = customer;
        this.price = price;
        this.bucket = bucket;
        this.isDeliveryFree = isDeliveryFree;
        this.isDeliverable = isDeliverable;
        this.orderAddress = orderAddress;
        this.status = status;
        this.commentary = "";
        this.createdAt = createdAt;
        this.requiredDeliveryTime = requiredDeliveryTime;
        this.isBonusUsed = isBonusUsed;
        this.isPromoCodeUsed = isPromoCodeUsed;
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

    public boolean isDeliverable() {
        return isDeliverable;
    }

    public void setDeliverable(boolean deliverable) {
        isDeliverable = deliverable;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPromoCodeUsed() {
        return isPromoCodeUsed;
    }

    public void setPromoCodeUsed(boolean promoCodeUsed) {
        isPromoCodeUsed = promoCodeUsed;
    }

    public boolean isBonusUsed() {
        return isBonusUsed;
    }

    public void setBonusUsed(boolean bonusUsed) {
        isBonusUsed = bonusUsed;
    }

    public LocalDateTime getRequiredDeliveryTime() {
        return requiredDeliveryTime;
    }

    public void setRequiredDeliveryTime(LocalDateTime requiredDeliveryTime) {
        this.requiredDeliveryTime = requiredDeliveryTime;
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
