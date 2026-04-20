// This class manages the food storage using an array-based circular queue.
public class FoodQueue {
    private FoodItem[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Constructor to create the queue with a fixed capacity
    public FoodQueue(int capacity) {
        this.capacity = capacity;
        queue = new FoodItem[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Checks if the queue has no items
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks if the queue reached its maximum capacity
    public boolean isFull() {
        return size == capacity;
    }

    // Adds a food item to the rear of the queue
    public void enqueue(FoodItem item) {
        if (isFull()) {
            System.out.println();
            System.out.println("--------------- ADD FOOD ITEM ---------------");
            System.out.println();
            System.out.println("Food storage is full. Item not added.");
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println();
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;

        System.out.println();
        System.out.println("--------------- FOOD ITEM ADDED -------------");
        System.out.println();
        item.displayItem();
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    // Removes the front item following FIFO order
    public FoodItem dequeue() {
        if (isEmpty()) {
            System.out.println();
            System.out.println("----------- FRONT ITEM REMOVED (FIFO) -----------");
            System.out.println();
            System.out.println("Queue is empty.");
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println();
            return null;
        }

        FoodItem item = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;

        if (size == 0) {
            front = 0;
            rear = -1;
        }

        return item;
    }

    // Removes the rear item following LIFO order
    public FoodItem removeLast() {
        if (isEmpty()) {
            System.out.println();
            System.out.println("------------ REAR ITEM REMOVED (LIFO) -----------");
            System.out.println();
            System.out.println("Queue is empty.");
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println();
            return null;
        }

        FoodItem item = queue[rear];
        queue[rear] = null;
        rear = (rear - 1 + capacity) % capacity;
        size--;

        if (size == 0) {
            front = 0;
            rear = -1;
        }

        return item;
    }

    // Displays all items from rear to front
    public void displayQueue() {
        System.out.println();
        System.out.println("--------------- FOOD STORAGE ----------------");
        System.out.println();

        if (isEmpty()) {
            System.out.println("Food storage is empty.");
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.println();
            return;
        }

        for (int i = 0; i < size; i++) {
            int index = (rear - i + capacity) % capacity;
            queue[index].displayItem();
            System.out.println();
        }

        System.out.println("---------------------------------------------");
        System.out.println();
    }

    // Displays the item at the front of the queue
    public void displayFrontItem() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        queue[front].displayItem();
    }

    // Searches for food items by name in the queue
    public void searchByName(String searchName) {
        System.out.println();
        System.out.println("-------------- SEARCH RESULT --------------");
        System.out.println();

        if (isEmpty()) {
            System.out.println("Food storage is empty.");
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println();
            return;
        }

        boolean found = false;

        // This loop checks each item using circular indexing
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;

            if (queue[index].getName().equalsIgnoreCase(searchName)) {
                queue[index].displayItem();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        System.out.println();
    }

    // Returns the current number of items in the queue
    public int getSize() {
        return size;
    }
}