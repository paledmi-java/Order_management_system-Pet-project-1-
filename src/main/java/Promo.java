import java.time.LocalDate;
import java.util.Random;

public class Promo {
    Random random = new Random();
    String promoCode;
    int discount;
    LocalDate startingDate;
    LocalDate endingDate;
    Item discountItem;
    boolean isBirthday;

    public Promo(String promoCode, int discount, LocalDate startingDate, LocalDate endingDate) {
        this.promoCode = promoCode;
        this.discount = discount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Promo(int discount, LocalDate startingDate, LocalDate endingDate) {
        this.promoCode = String.valueOf(random.nextInt(100000, 999999));
        this.discount = discount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Promo(int discount) {
        this.promoCode = String.valueOf(random.nextInt(100000, 999999));
        this.discount = discount;
        this.startingDate = LocalDate.now();
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public Item getDiscountItem() {
        return discountItem;
    }

    public void setDiscountItem(Item discountItem) {
        this.discountItem = discountItem;
    }

    public boolean isBirthday() {
        return isBirthday;
    }

    public void setBirthday(boolean birthday) {
        isBirthday = birthday;
    }
}
