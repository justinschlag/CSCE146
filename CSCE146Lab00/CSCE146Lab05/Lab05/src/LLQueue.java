// Justin Schlag 2025
// Implements QueueI<T> using a linked list.

public class LLQueue<T> implements QueueI<T> {
    
    // Inner class representing a node in the linked list
    private static class Node<T> {
        T data; // Stores the data of the node
        Node<T> next; // Reference to the next node

        // Constructor to initialize node with data
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // Points to the first element in the queue
    private Node<T> tail; // Points to the last element in the queue

    // Constructor initializes an empty queue
    public LLQueue() {
        head = null;
        tail = null;
    }

    // Adds an element to the end of the queue
    @Override
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = tail;
        }
    }

    // Removes and returns the first element from the queue
    @Override
    public T dequeue() {
        if (head == null) {
            return null; // Returns null if the queue is empty
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null; // If the queue becomes empty, reset tail
        }
        return data;
    }

    // Returns the first element without removing it
    @Override
    public T peek() {
        return (head != null) ? head.data : null;
    }

    // Prints all elements in the queue, formatted as required
    @Override
    public void print() {
        if (head == null) {
            System.out.println("***No processes in queue***"); // Matches expected output format
            return;
        }

        Node<T> current = head;
        while (current != null) {
            System.out.println("***" + current.data + "***"); // Encloses output for consistency
            current = current.next;
        }
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }
}
