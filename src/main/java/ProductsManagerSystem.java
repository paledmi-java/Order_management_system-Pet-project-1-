import java.util.*;

public class ProductsManagerSystem implements ProductsManageable{
    private Random random = new Random();
    private static int itemIdCounter = 0;
    private final ArrayList<Item> allItems = new ArrayList<>();
    private final HashMap<String, Item> searchOfItem = new HashMap<>();
    private final TreeSet<Item> sortedItemsByPrice = new TreeSet<>();
    private final HashMap<String, List<Item>> groupsOfItemsByType = new HashMap<>();

    @Override
    public void addAnItemToSystem(Item item) {
        allItems.add(item);
        searchOfItem.put(item.getItemName(),item);
        sortedItemsByPrice.add(item);

        String type = item.getTypeOfItem();

            if(groupsOfItemsByType.containsKey(type)){
                groupsOfItemsByType.get(type).add(item);
            }
            ArrayList<Item> newGroup = new ArrayList<>();
            newGroup.add(item);
            groupsOfItemsByType.put(type + "'s", newGroup);
        }

    @Override
    public Item createAnItem(String name, String typeOfItem, String ingredients, int amountOfPieces, int price, String description,
                             int mass, int kcal, String imageURL, boolean hasMultiComp,
                             boolean isChangeable, boolean isAvailable){
        return  new Item(itemIdCounter++, name, typeOfItem, ingredients, amountOfPieces, price, description, mass, kcal,
                 imageURL, hasMultiComp, isChangeable, isAvailable);
    }

    public void getEveryTypeOfProducts(){
        System.out.println(groupsOfItemsByType.keySet());;
    }

    public void getFullMenu(){
        int i = 1;
        for(Item item : allItems){
            System.out.println(i + ") " + item);
            i++;
        }
    }


    public int getItemIdCounter() {
        return itemIdCounter;
    }

    public void setItemIdCounter(int itemIdCounter) {
        this.itemIdCounter = itemIdCounter;
    }

    public ArrayList<Item> getAllItemsList() {
        return allItems;
    }

    public HashMap<String, Item> getSearchOfItemList() {
        return searchOfItem;
    }

    public TreeSet<Item> getSortedItemsByPriceList() {
        return sortedItemsByPrice;
    }

    public HashMap<String, List<Item>> getGroupsOfItemsByType() {
        return groupsOfItemsByType;
    }

    public void fillTheFoodMenu(){
        Item item1 = createAnItem("Roll Dragon", "Roll",
                "Rice, Dragon", 8, 593,
                "Roll made out of Dragon", 230, 237,
                String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true );

        addAnItemToSystem(item1);

        Item item2 = createAnItem("Tempura Roll with eel",
                "Tempura Roll", "Tempura, Rice, Eel", 8,
                447, "Tempura Roll made out of eel", 330, 455,
                String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true);

        addAnItemToSystem(item2);

        Item item3 = createAnItem("Salmon Roll with spicy mango sauce",
                "Baked roll", "Salmon, Rice, Spicy sauce",
                8, 556, "Baked Salmon Roll with spicy mango sauce",
                456, 677,  String.valueOf(random.nextInt(100000, 9999999)),
                false, false, true);

        addAnItemToSystem(item3);
        System.out.println("Items are added");
    }

}
