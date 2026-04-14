
import java.time.LocalDate;
import java.time.LocalTime;

public class FoodItem extends Item {
    double weight;
    LocalDate bestBeforeDate;
    String timeAdded;

    public FoodItem(String name, double weight, LocalDate bestBeforeDate) {
        super(name);
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
        this.timeAdded = LocalTime.now().toString();
    }

    public void displayItem() {
        System.out.println("Name: " + name);
        System.out.println("Weight: " + weight + "g");
        System.out.println("Best Before Date: " + bestBeforeDate);
        System.out.println("Time Added: " + timeAdded);
        System.out.println("-----------------------------");
    }
}