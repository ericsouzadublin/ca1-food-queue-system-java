public class FoodQueue {
    private FoodItem[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public FoodQueue(int capacity) {
        this.capacity = capacity;
        queue = new FoodItem[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(FoodItem item) {
        if (isFull()) {
            System.out.println("Queue is full.");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
        System.out.println("Item added.");
    }

    public FoodItem dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }

        FoodItem item = queue[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public FoodItem removeLast() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
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

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("\nFood storage is empty.\n");
            return;
        }

        System.out.println();
        System.out.println("---------- FOOD STORAGE (Rear to Front) ----------");
        System.out.println();

        for (int i = 0; i < size; i++) {
            int index = (rear - i + capacity) % capacity;

            queue[index].displayItem();
            System.out.println(); // espaço entre itens
        }

        System.out.println("-------------------------------------------------");
        System.out.println();
    }
    
    public void displayFrontItem() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        queue[front].displayItem();
    }

    public void searchByName(String searchName) {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (queue[index].name.equalsIgnoreCase(searchName)) {
                queue[index].displayItem();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    public int getSize() {
        return size;
    }
}