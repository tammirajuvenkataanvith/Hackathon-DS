package socialmedia;

import java.io.*;
import java.util.Scanner;


class User {
    String userId;
    String name;
    String interest;
    int mutualFriendCount;
    
    public User(String userId, String name, String interest, int mutualFriendCount) {
        this.userId = userId;
        this.name = name;
        this.interest = interest;
        this.mutualFriendCount = mutualFriendCount;
    }
    
    public String toString() {
        return userId + "," + name + "," + interest + "," + mutualFriendCount;
    }
}

class Node {

    User data;
    Node next;
    
    public Node(User data) {
        this.data = data;
        this.next = null;
    }
}



class LinkedList {
    Node head;
    int size;
    
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    
    public void add(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }
    
    
    public void display() {
        if (head == null) {
            System.out.println("No users found!");
            return;
        }
        
        System.out.println("\n========== USER LIST ==========");
        System.out.println("UserID,Name,Interest,MutualFriends");
        System.out.println("================================");
        
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println("================================\n");
    }
    
    
    public User[] toArray() {
        if (size == 0) return new User[0];
        
        User[] arr = new User[size];
        Node temp = head;
        int i = 0;
        while (temp != null) {
            arr[i++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }
    
    
    public void fromArray(User[] arr) {
        head = null;
        size = 0;
        for (User user : arr) {
            if (user != null) {
                add(user);
            }
        }
    }
    
   
    public boolean remove(String userId) {
        if (head == null) return false;
        
        if (head.data.userId.equals(userId)) {
            head = head.next;
            size--;
            return true;
        }
        
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.userId.equals(userId)) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}



class CircularQueue {
    User[] queue;
    int front, rear, size, capacity;
    
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new User[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public boolean enqueue(User user) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return false;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = user;
        size++;
        return true;
    }
    
    public User dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        User user = queue[front];
        front = (front + 1) % capacity;
        size--;
        return user;
    }
    
    public User peek() {
        if (isEmpty()) return null;
        return queue[front];
    }
}


// ==================== PRIORITY QUEUE USING MIN HEAP ====================
class PriorityQueue {
    User[] heap;
    int size;
    int capacity;
    
    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap = new User[capacity];
        this.size = 0;
    }
    
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
    
    private void swap(int i, int j) {
        User temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    
    public boolean insert(User user) {
        if (size == capacity) {
            System.out.println("Priority Queue is full!");
            return false;
        }
        
        heap[size] = user;
        int current = size;
        size++;
        
        
        while (current > 0 && heap[current].mutualFriendCount > heap[parent(current)].mutualFriendCount) {
            swap(current, parent(current));
            current = parent(current);
        }
        return true;
    }
    
    
    public User extractMax() {
        if (size == 0) {
            System.out.println("Priority Queue is empty!");
            return null;
        }
        
        User max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }
    
    private void heapifyDown(int i) {
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);
        
        if (left < size && heap[left].mutualFriendCount > heap[maxIndex].mutualFriendCount) {
            maxIndex = left;
        }
        if (right < size && heap[right].mutualFriendCount > heap[maxIndex].mutualFriendCount) {
            maxIndex = right;
        }
        
        if (i != maxIndex) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}



class Action {
    String actionType; 
    User user;
    
    public Action(String actionType, User user) {
        this.actionType = actionType;
        this.user = user;
    }
}

class Stack {
    Action[] stack;
    int top;
    int capacity;
    
    public Stack(int capacity) {
        this.capacity = capacity;
        this.stack = new Action[capacity];
        this.top = -1;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    public boolean push(Action action) {
        if (isFull()) {
            System.out.println("Stack overflow! Cannot push more actions.");
            return false;
        }
        stack[++top] = action;
        return true;
    }
    
    public Action pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! No actions to undo.");
            return null;
        }
        return stack[top--];
    }
    
    public Action peek() {
        if (isEmpty()) return null;
        return stack[top];
    }
}



class HashNode {
    String key;
    User value;
    HashNode next;
    
    public HashNode(String key, User value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashTable {
    HashNode[] table;
    int capacity;
    int size;
    
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }
    
    
    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        return hashValue % capacity;
    }
    
    
    public void insert(String key, User value) {
        int index = hash(key);
        HashNode newNode = new HashNode(key, value);
        
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // Chaining - add to end of chain
            HashNode temp = table[index];
            while (temp.next != null) {
                if (temp.key.equals(key)) {
                    temp.value = value; // Update if key exists
                    return;
                }
                temp = temp.next;
            }
            if (temp.key.equals(key)) {
                temp.value = value;
            } else {
                temp.next = newNode;
            }
        }
        size++;
    }
    
    
    public User search(String key) {
        int index = hash(key);
        HashNode temp = table[index];
        
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }
    
    
    public void display() {
        System.out.println("\n========== HASH TABLE (Processed Users) ==========");
        boolean found = false;
        
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.print("Index " + i + ": ");
                HashNode temp = table[i];
                while (temp != null) {
                    System.out.print("[" + temp.value + "] ");
                    temp = temp.next;
                    found = true;
                }
                System.out.println();
            }
        }
        
        if (!found) {
            System.out.println("No processed users in hash table.");
        }
        System.out.println("==================================================\n");
    }
}



public class main {
    
    static LinkedList userList = new LinkedList();
    static CircularQueue processingQueue = new CircularQueue(50);
    static PriorityQueue priorityQueue = new PriorityQueue(50);
    static Stack actionStack = new Stack(100);
    static HashTable processedUsers = new HashTable(50);
    static Scanner scanner = new Scanner(System.in);
    static String filePath = "C:\\Users\\anvit\\OneDrive\\Desktop\\Friend Suggestion System.txt";
    
    
    
    public static void quickSort(User[] arr, int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(arr, low, high, sortBy);
            quickSort(arr, low, pi - 1, sortBy);
            quickSort(arr, pi + 1, high, sortBy);
        }
    }
    
    private static int partition(User[] arr, int low, int high, String sortBy) {
        User pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            boolean shouldSwap = false;
            
            if (sortBy.equals("mutualFriends")) {
                shouldSwap = arr[j].mutualFriendCount < pivot.mutualFriendCount;
            } else if (sortBy.equals("interest")) {
                shouldSwap = arr[j].interest.compareToIgnoreCase(pivot.interest) < 0;
            }
            
            if (shouldSwap) {
                i++;
                User temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        User temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    
    
    public static User binarySearchByID(User[] arr, String userId) {
        
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].userId.compareToIgnoreCase(arr[j + 1].userId) > 0) {
                    User temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = arr[mid].userId.compareToIgnoreCase(userId);
            
            if (cmp == 0) return arr[mid];
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
    
   
    public static void linearSearchByInterest(User[] arr, String interest) {
        boolean found = false;
        System.out.println("\n========== SEARCH RESULTS (Interest: " + interest + ") ==========");
        
        for (User user : arr) {
            if (user.interest.equalsIgnoreCase(interest)) {
                System.out.println(user);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No users found with interest: " + interest);
        }
        System.out.println("==================================================\n");
    }
    
    
    
    
    public static void saveToFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            Node temp = userList.head;
            while (temp != null) {
                bufferedWriter.write(temp.data.toString());
                bufferedWriter.newLine();
                temp = temp.next;
            }
            
            bufferedWriter.close();
            writer.close();
            System.out.println("\n✓ Data saved successfully to file!");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    
    public static void loadFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("No previous data found. Starting fresh.");
                return;
            }
            
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    User user = new User(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                    userList.add(user);
                    count++;
                }
            }
            
            bufferedReader.close();
            reader.close();
            System.out.println("✓ Loaded " + count + " users from file.");
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
    
    
   
    
    public static void addUser() {
        System.out.println("\n========== ADD NEW USER ==========");
        
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Interest: ");
        String interest = scanner.nextLine();
        
        System.out.print("Enter Mutual Friend Count: ");
        int mutualFriendCount = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        User newUser = new User(userId, name, interest, mutualFriendCount);
        userList.add(newUser);
        
        
        actionStack.push(new Action("ADD", newUser));
        
        System.out.println("✓ User added successfully!");
        System.out.println("==================================\n");
    }
    
    public static void searchByID() {
        System.out.println("\n========== SEARCH BY ID ==========");
        System.out.print("Enter User ID to search: ");
        String userId = scanner.nextLine();
        
        User[] arr = userList.toArray();
        if (arr.length == 0) {
            System.out.println("No users in the system!");
            return;
        }
        
        User found = binarySearchByID(arr, userId);
        if (found != null) {
            System.out.println("\n✓ User Found:");
            System.out.println(found);
        } else {
            System.out.println("\n✗ User not found with ID: " + userId);
        }
        System.out.println("==================================\n");
    }
    
    public static void searchByInterest() {
        System.out.println("\n========== SEARCH BY INTEREST ==========");
        System.out.print("Enter Interest to search: ");
        String interest = scanner.nextLine();
        
        User[] arr = userList.toArray();
        if (arr.length == 0) {
            System.out.println("No users in the system!");
            return;
        }
        
        linearSearchByInterest(arr, interest);
    }
    
    public static void sortRecords() {
        User[] arr = userList.toArray();
        if (arr.length == 0) {
            System.out.println("No users to sort!");
            return;
        }
        
        System.out.println("\n========== SORT RECORDS ==========");
        System.out.println("1. Sort by Mutual Friend Count");
        System.out.println("2. Sort by Interest");
        System.out.print("Choose sorting criteria: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        String sortBy = "";
        if (choice == 1) {
            sortBy = "mutualFriends";
            quickSort(arr, 0, arr.length - 1, sortBy);
            System.out.println("✓ Sorted by Mutual Friend Count (descending)");
        } else if (choice == 2) {
            sortBy = "interest";
            quickSort(arr, 0, arr.length - 1, sortBy);
            System.out.println("✓ Sorted by Interest (alphabetically)");
        } else {
            System.out.println("Invalid choice!");
            return;
        }
        
        
        userList.fromArray(arr);
        userList.display();
    }
    
    public static void processNext() {
        System.out.println("\n========== PROCESS NEXT USER ==========");
        
        
        Node temp = userList.head;
        int added = 0;
        while (temp != null) {
            if (processingQueue.enqueue(temp.data)) {
                added++;
            }
            temp = temp.next;
        }
        
        if (added > 0) {
            System.out.println("✓ Added " + added + " users to processing queue");
        }
        
       
        User nextUser = processingQueue.dequeue();
        if (nextUser != null) {
            System.out.println("\n✓ Processing User:");
            System.out.println(nextUser);
            
            
            processedUsers.insert(nextUser.userId, nextUser);
            System.out.println("✓ User stored in hash table");
        }
        System.out.println("=======================================\n");
    }
    
    public static void prioritizeUsers() {
        System.out.println("\n========== PRIORITY PROCESSING ==========");
        
        
        Node temp = userList.head;
        int added = 0;
        while (temp != null) {
            if (priorityQueue.insert(temp.data)) {
                added++;
            }
            temp = temp.next;
        }
        
        System.out.println("✓ Added " + added + " users to priority queue");
        System.out.println("\nProcessing users by priority (Mutual Friends Count):\n");
        
        
        int count = 1;
        while (!priorityQueue.isEmpty()) {
            User user = priorityQueue.extractMax();
            System.out.println("Priority " + count + ": " + user);
            processedUsers.insert(user.userId, user);
            count++;
        }
        
        System.out.println("\n✓ All users processed and stored in hash table");
        System.out.println("==========================================\n");
    }
    
    public static void undoLastAction() {
        System.out.println("\n========== UNDO LAST ACTION ==========");
        
        Action lastAction = actionStack.pop();
        if (lastAction == null) {
            System.out.println("No actions to undo!");
            return;
        }
        
        if (lastAction.actionType.equals("ADD")) {
            
            boolean removed = userList.remove(lastAction.user.userId);
            if (removed) {
                System.out.println("✓ Undone: Removed user " + lastAction.user.name);
            } else {
                System.out.println("✗ Failed to undo action");
            }
        } else if (lastAction.actionType.equals("REMOVE")) {
            
            userList.add(lastAction.user);
            System.out.println("✓ Undone: Added back user " + lastAction.user.name);
        }
        
        System.out.println("======================================\n");
    }
    
    
    
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   SOCIAL MEDIA FRIEND SUGGESTION SYSTEM           ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // Load existing data from file
        loadFromFile();
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n╔════════════════ MAIN MENU ═════════════════╗");
            System.out.println("║  1. Add User                                ║");
            System.out.println("║  2. Search by ID                            ║");
            System.out.println("║  3. Search by Interest                      ║");
            System.out.println("║  4. Sort Records                            ║");
            System.out.println("║  5. Process Next                            ║");
            System.out.println("║  6. Prioritize Queue                        ║");
            System.out.println("║  7. Undo Last Action                        ║");
            System.out.println("║  8. View Hash Table                         ║");
            System.out.println("║  9. Display All Users                       ║");
            System.out.println("║ 10. Save to File                            ║");
            System.out.println("║ 11. Exit                                    ║");
            System.out.println("╚═════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addUser();
                    break;
                    
                case 2:
                    searchByID();
                    break;
                    
                case 3:
                    searchByInterest();
                    break;
                    
                case 4:
                    sortRecords();
                    break;
                    
                case 5:
                    processNext();
                    break;
                    
                case 6:
                    prioritizeUsers();
                    break;
                    
                case 7:
                    undoLastAction();
                    break;
                    
                case 8:
                    processedUsers.display();
                    break;
                    
                case 9:
                    userList.display();
                    break;
                    
                case 10:
                    saveToFile();
                    break;
                    
                case 11:
                    System.out.println("\n========================================");
                    System.out.print("Save data before exiting? (y/n): ");
                    String save = scanner.nextLine();
                    if (save.equalsIgnoreCase("y")) {
                        saveToFile();
                    }
                    System.out.println("\n✓ Thank you for using the system!");
                    System.out.println("========================================\n");
                    running = false;
                    break;
                    
                default:
                    System.out.println("\n✗ Invalid choice! Please try again.\n");
            }
        }
        
        scanner.close();
    }
}