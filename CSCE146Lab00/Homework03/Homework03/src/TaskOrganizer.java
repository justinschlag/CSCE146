//Justin Schlag 2025
// Homework 03: Task Organizer

import java.io.*;
import java.util.Scanner;

// Generic Node class representing a single node in the linked list
class Node<T> {
    T data; // Holds the task data
    Node<T> next; // Points to the next node in the list
    
    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Generic Linked List class
class GenLL<T> {
    private Node<T> head; // Head of the linked list

    public Node<T> getHead() {
        return head;
    }
    
    // Adds a new task to the end of the list to maintain correct order
    public void add(T data) {
        if (contains(data)) {
            System.out.println("Duplicate task not added.");
            return;
        }
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    // Removes a task from the list
    public boolean remove(T data) {
        if (head == null) return false;
        if (head.data.equals(data)) {
            head = head.next;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Checks if a task already exists in the list
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }
    
    // Prints all tasks in the linked list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}

// Task class that holds the task information
class Task {
    String action; // Task description
    int priority; // Task priority level (0-4)
    
    public Task(String action, int priority) {
        this.action = action;
        this.priority = priority;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return priority == task.priority && action.equals(task.action);
    }
    
    @Override
    public String toString() {
        return "[Task] Priority: " + priority + " Task: " + action;
    }
}

// TaskManager class for managing multiple tasks
class TaskManager {
    private GenLL<Task>[] tasks; // Array of linked lists to store tasks by priority
    
    @SuppressWarnings("unchecked")
    public TaskManager() {
        tasks = new GenLL[5];
        for (int i = 0; i < 5; i++) {
            tasks[i] = new GenLL<>();
        }
    }
    
    // Adds a task to the appropriate priority list
    public void addTask(String action, int priority) {
        if (priority < 0 || priority > 4) {
            System.out.println("Invalid priority! Use values between 0-4.");
            return;
        }
        tasks[priority].add(new Task(action, priority));
    }
    
    // Removes a task from the appropriate priority list
    public void removeTask(String action, int priority) {
        if (priority < 0 || priority > 4) {
            System.out.println("Invalid priority!");
            return;
        }
        if (!tasks[priority].remove(new Task(action, priority))) {
            System.out.println("Task not found.");
        }
    }
    
    // Prints all tasks sorted by priority (0 being highest, 4 being lowest)
    public void printTasks() {
        for (int i = 0; i < 5; i++) {
            tasks[i].printList();
        }
    }
    
    // Reads tasks from a file and loads them into the system
    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\t");
                if (data.length == 2) {
                    try {
                        int priority = Integer.parseInt(data[0]);
                        addTask(data[1], priority);
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
    
    // Writes all tasks to a file in the correct format
    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (int i = 0; i < 5; i++) {
                Node<Task> current = tasks[i].getHead();
                while (current != null) {
                    writer.println(current.data.priority + "\t" + current.data.action);
                    current = current.next;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}

// Main class with user interface
public class TaskOrganizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        
        System.out.println("Welcome to the Task Organizer!"); // Initial welcome message
        
        int choice;
        do {
            // Display menu options
            System.out.println("\nEnter 1. To Add a Task");
            System.out.println("Enter 2. To Remove a Task");
            System.out.println("Enter 3. To Print Tasks To Console");
            System.out.println("Enter 4. To Read from a Task File");
            System.out.println("Enter 5. To Write to a Task File");
            System.out.println("Enter 9. To Quit");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter the task's priority");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the task's action");
                    String action = scanner.nextLine();
                    manager.addTask(action, priority);
                    break;
                case 2:
                    System.out.println("Enter the task's priority");
                    int remPriority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the task's action");
                    String remAction = scanner.nextLine();
                    manager.removeTask(remAction, remPriority);
                    break;
                case 3:
                    manager.printTasks();
                    break;
                case 4:
                    System.out.println("Enter the file name");
                    String readFile = scanner.nextLine();
                    manager.readFromFile(readFile);
                    break;
                case 5:
                    System.out.println("Enter the file name");
                    String writeFile = scanner.nextLine();
                    manager.writeToFile(writeFile);
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (choice != 9);
    }
}
