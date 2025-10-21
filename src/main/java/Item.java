import java.util.Objects;

public class Item implements Comparable<Item>{
    private int itemId;
    private String name;
    private String type;
    private String ingredients;
    private int amountOfPieces;
    private int price;
    private String description;
    private int mass;
    private int kcal;
    private String imageURL;
    private boolean hasMultiComp;
    private boolean isChangeable;
    private boolean isAvailable;

    public Item(int itemId){
        this.itemId = itemId;
    }


    public Item(int itemId, String name, String type, String ingredients, int amountOfPieces, int price, String description,
                int mass, int kcal, String imageURL, boolean hasMultiComp,
                boolean isChangeable, boolean isAvailable) {
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
        this.amountOfPieces = amountOfPieces;
        this.price = price;
        this.description = description;
        this.mass = mass;
        this.kcal = kcal;
        this.imageURL = imageURL;
        this.hasMultiComp = hasMultiComp;
        this.isChangeable = isChangeable;
        this.isAvailable = isAvailable;
    }


    public enum itemFormatType {FULL, PUBLIC, SHORT}

    public String itemFormat(itemFormatType itemFormatType){
        return switch (itemFormatType){
            case FULL -> String.format("Item: Id-%d, Name: %s, Type: %s, Ingredients: %s, Amount Of Pieces: %d, " +
                            "Price: %d, Mass: %d, Kcal: %d, Has multiple components: %b, " +
                            "Is changeable: %b, Is available: %b",
                    itemId, name, type, ingredients, amountOfPieces, price, mass, kcal,
                    hasMultiComp, isChangeable, isAvailable);
            case SHORT -> String.format("%s, %d pieces, " +
                            " Mass: %d gr, Price: %d rub",
                    name, amountOfPieces, mass, price);
            case PUBLIC -> String.format("Name: %s, Type: %s, Ingredients: %s, Amount Of Pieces: %d, " +
                            "Price: %d, Mass: %d, Kcal: %d" ,
                    name, type, ingredients, amountOfPieces, price, mass, kcal);
        };
    }

    public String toString(){
        return String.format("%s, %d pieces, " +
                        " Mass: %d gr, Price: %d rub",
                name, amountOfPieces, mass, price);
    }



    @Override
    public int compareTo(Item o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name);
    }


    public int getAmountOfPieces() {
        return amountOfPieces;
    }


    public void setAmountOfPieces(int amountOfPieces) {
        this.amountOfPieces = amountOfPieces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isMultiComp() {
        return hasMultiComp;
    }

    public void setMultiComp(boolean multiComp) {
        hasMultiComp = multiComp;
    }

    public boolean isChangeable() {
        return isChangeable;
    }

    public void setChangeable(boolean changeable) {
        isChangeable = changeable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
