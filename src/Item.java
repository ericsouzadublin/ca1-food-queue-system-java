// This class represents a basic item in the system.
// It is used as a parent class for FoodItem.
public class Item {

    // The name of the item (e.g. Burger, Pizza)
    private String name;

    // Constructor to create an item with a name
    public Item(String name) {
        this.name = name;
    }

    // Getter to access the name safely
    public String getName() {
        return name;
    }
}