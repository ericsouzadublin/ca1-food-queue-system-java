import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodQueue foodQueue = new FoodQueue(8);
        int choice;

        do {
            System.out.println("1. Add food item");
            System.out.println("2. Remove front food item (FIFO)");
            System.out.println("3. Remove rear food item (LIFO)");
            System.out.println("4. Display all food items");
            System.out.println("5. Display front food item");
            System.out.println("6. Display queue size");
            System.out.println("7. Search food item by name");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                System.out.print("Enter your choice: ");
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Select food item:");
                    System.out.println("1. Burger");
                    System.out.println("2. Pizza");
                    System.out.println("3. Fries");
                    System.out.println("4. Sandwich");
                    System.out.println("5. Hotdog");
                    System.out.print("Enter your choice: ");

                    int foodChoice;

                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine();
                        System.out.print("Enter your choice: ");
                    }

                    while (true) {
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                            System.out.print("Enter your choice: ");
                            continue;
                        }

                        foodChoice = sc.nextInt();
                        sc.nextLine();

                        if (foodChoice >= 1 && foodChoice <= 5) {
                            break;
                        }

                        System.out.println("Invalid choice. Please select a number between 1 and 5.");
                        System.out.print("Enter your choice: ");
                    }

                    String name = "";

                    switch (foodChoice) {
                        case 1:
                             name = "Burger";
                             break;
                        case 2:
                            name = "Pizza";
                            break;
                        case 3:
                            name = "Fries";
                            break;
                        case 4:
                            name = "Sandwich";
                            break;
                        case 5:
                            name = "Hotdog";
                            break;
                        default:
                            System.out.println("Invalid choice. Item not added.");
                            break;
                    }

                    int weight;

                    while (true) {
                        System.out.print("Enter weight in grams: ");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Weight must be a whole number.");
                            sc.nextLine();
                            continue;
                        }

                        weight = sc.nextInt();
                        sc.nextLine();

                        if (weight > 0) {
                            break;
                        }

                        System.out.println("Invalid weight. Enter grams greater than 0.");
                    }

                    System.out.print("Enter best before date (dd/MM/yyyy): ");
                    String bestBeforeDate = sc.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate bestBefore;

                    while (true) {
                        try {
                            bestBefore = LocalDate.parse(bestBeforeDate, formatter);

                            LocalDate today = LocalDate.now();
                            LocalDate maxDate = today.plusWeeks(2);

                            if (bestBefore.isBefore(today)) {
                                System.out.println("Date cannot be in the past.");
                            } else if (bestBefore.isAfter(maxDate)) {
                                System.out.println("Date cannot be more than 2 weeks in the future.");
                            } else {
                                break;
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date. Use format dd/MM/yyyy.");
                        }
                        
                        System.out.print("Enter best before date (dd/MM/yyyy): ");
                        bestBeforeDate = sc.nextLine();
                            
                    }

                    FoodItem newItem = new FoodItem(name, weight, bestBefore);
                    foodQueue.enqueue(newItem);
                    break;

                case 2:
                    FoodItem removedItem = foodQueue.dequeue();
                    if (removedItem != null) {
                        System.out.println("Front item removed: ");
                        removedItem.displayItem();
                    }
                    break;
                    
                case 3:
                    FoodItem lastRemovedItem = foodQueue.removeLast();
                    if (lastRemovedItem != null) {
                        System.out.println("Rear item removed: ");
                        lastRemovedItem.displayItem();
                    }
                    break;

                case 4:
                    foodQueue.displayQueue();
                    break;

                case 5:
                    System.out.println("Front item:");
                    foodQueue.displayFrontItem();
                    break;

                case 6:
                    System.out.println("Current queue size: " + foodQueue.getSize());
                    break;

                case 7:
                    System.out.print("Enter food name to search: ");
                    String searchName = sc.nextLine();

                    if (searchName.trim().isEmpty()) {
                        System.out.println("Search name cannot be empty.");
                        break;
                    }
                    
                    foodQueue.searchByName(searchName);
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}