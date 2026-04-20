import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodQueue foodQueue = new FoodQueue(8);
        int choice;

        // Main menu loop
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
            
            // Validates menu input to make sure it is a number
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                System.out.print("Enter your choice: ");
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Lets the user choose one of the 5 allowed food items
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

                    // Repeats until the user enters a valid option from 1 to 5
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

                    // Converts the numeric choice into the food name
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

                    // Validates weight input
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

                    // Validates the date format and checks if the date is within 2 weeks
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
                    // Removes the front item using FIFO logic
                    FoodItem removedItem = foodQueue.dequeue();
                    if (removedItem != null) {
                        System.out.println();
                        System.out.println("----------- FRONT ITEM REMOVED (FIFO) -----------");
                        System.out.println();

                        removedItem.displayItem();

                        System.out.println("-------------------------------------------------");
                        System.out.println();
                    }
                    break;
                    
                case 3:
                    // Removes the rear item using LIFO logic
                    FoodItem lastRemovedItem = foodQueue.removeLast();
                    if (lastRemovedItem != null) {
                        System.out.println();
                        System.out.println("------------ REAR ITEM REMOVED (LIFO) -----------");
                        System.out.println();

                        lastRemovedItem.displayItem();

                        System.out.println("-------------------------------------------------");
                        System.out.println();
                    }
                    break;

                case 4:
                    // Displays all items currently in the food storage
                    foodQueue.displayQueue();
                    break;

                case 5:
                    // Displays the item at the front of the queue
                    System.out.println();
                    System.out.println("---------- FRONT ITEM (PEEK) ----------");
                    System.out.println();

                    foodQueue.displayFrontItem();

                    System.out.println();
                    System.out.println("---------------------------------------");
                    System.out.println();
                    break;

                case 6:
                    // Displays the current number of items in the queue
                    System.out.println();
                    System.out.println("--------------- QUEUE SIZE ---------------");
                    System.out.println();

                    System.out.println("Current queue size: " + foodQueue.getSize());

                    System.out.println();
                    System.out.println("------------------------------------------");
                    System.out.println();
                    break;

                case 7:
                    // Searches for food items by name
                    System.out.print("Enter food name to search: ");
                    String searchName = sc.nextLine();

                    if (searchName.trim().isEmpty()) {
                        System.out.println("Search name cannot be empty.");
                        break;
                    }
                    
                    foodQueue.searchByName(searchName);
                    break;

                case 0:
                    // Ends the program
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}