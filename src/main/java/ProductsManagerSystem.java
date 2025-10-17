import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class ProductsManagerSystem implements ProductsManageable{

    private int ItemIdCounter = 0;
    private final ArrayList<Item> allItems = new ArrayList<>();
    private final HashSet<Item> searchOfItem = new HashSet<>();
    private final TreeSet<Item> sortedItemsByPrice = new TreeSet<>();

    @Override
    public void addAnItemToSystem(Item item) {
        ItemIdCounter++;
        allItems.add(item);
        searchOfItem.add(item);
        sortedItemsByPrice.add(item);
    }

    public void createAnItem(){
        Item item = new Item();
    }

    @Override
    public int compareTo(Item o) {
        return 0;
    }
}
