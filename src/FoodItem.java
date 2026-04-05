public class FoodItem {
    String name;
    double weight;
    String bestBeforeDate;
    String timeAdded;

    public FoodItem(String name, double weight, String bestBeforeDate, String timeAdded) {
        this.name = name;
        this.weight = weight;
        this.bestBeforeDate = bestBeforeDate;
        this.timeAdded = timeAdded;
    }

    public void displayItem() {
        System.out.println("Name: " + name);
        System.out.println("Weight: " + weight + "g");
        System.out.println("Best Before Date: " + bestBeforeDate);
        System.out.println("Time Added: " + timeAdded);
        System.out.println("-----------------------------");
    }
}