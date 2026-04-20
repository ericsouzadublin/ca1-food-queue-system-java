import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// This class represents a food item stored in the system.
// It extends Item, so it inherits the name field.
public class FoodItem extends Item {
    
    // Formatter used to show the date in dd/MM/yyyy format
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // Weight of the food item in grams
    private double weight;

    // Best before date of the food item
    private LocalDate bestBeforeDate;

    // Time when the item was added to the storage
    private String timeAdded;

    // Constructor to create a food item with name, weight and best before date
    public FoodItem(String name, double weight, LocalDate bestBeforeDate) {
        super(name);
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
        this.timeAdded = LocalTime.now().toString();
    }

    // This method displays the details of one food item
    public void displayItem() {
        System.out.println("Name: " + getName());
        System.out.println("Weight: " + weight + "g");
        System.out.println("Best Before Date: " + bestBeforeDate.format(FORMATTER));
        System.out.println("Time Added: " + timeAdded);
        System.out.println("-----------------------------");
    }
}