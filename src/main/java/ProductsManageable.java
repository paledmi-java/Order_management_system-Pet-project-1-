public interface ProductsManageable{
    void addAnItemToSystem(Item item);
    Item createAnItem(String name, String typeOfItem, String ingredients, int amountOfPieces, int price, String description,
                      int mass, int kcal, String imageURL, boolean hasMultiComp,
                      boolean isChangeable, boolean isAvailable);
}
