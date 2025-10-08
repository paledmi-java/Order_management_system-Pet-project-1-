public class Item {
    private int itemId;
    private String name;
    private String ingredients;
    private int price;
    private String description;
    private int mass;
    private int kcal;
    private String imageURL;
    private boolean isMultiComp;
    private boolean isChangeable;
    private boolean isAvailable;

    public Item(int itemId, String name, String ingredients, int price, String description,
                int mass, int kcal, String imageURL, boolean isMultiComp,
                boolean isChangeable, boolean isAvailable) {
        this.itemId = itemId;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.description = description;
        this.mass = mass;
        this.kcal = kcal;
        this.imageURL = imageURL;
        this.isMultiComp = isMultiComp;
        this.isChangeable = isChangeable;
        this.isAvailable = isAvailable;
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
        return isMultiComp;
    }

    public void setMultiComp(boolean multiComp) {
        isMultiComp = multiComp;
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
