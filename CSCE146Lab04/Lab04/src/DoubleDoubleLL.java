// DoubleDoubleLL.java
// Justin Schlag 2025
// Implementation of a doubly linked list with  methods

public class DoubleDoubleLL {
    
    // Internal class representing a node in the doubly linked list
    private class Node {
        double data;
        Node next;
        Node prev;

        public Node(double data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head; // Points to the first node
    private Node tail; // Points to the last node
    private Node current; // Points to the current node for traversal

    // Constructor: Initializes an empty doubly linked list
    public DoubleDoubleLL() {
        head = null;
        tail = null;
        current = null;
    }

    // Moves the current pointer to the next node
    public void gotoNext() {
        if (current != null) current = current.next;
    }

    // Moves the current pointer to the previous node
    public void gotoPrev() {
        if (current != null) current = current.prev;
    }

    // Resets the current pointer to the head of the list
    public void reset() {
        current = head;
    }

    // Moves the current pointer to the last node in the list
    public void gotoEnd() {
        current = tail;
    }

    // Returns true if there are more elements to traverse
    public boolean hasMore() {
        return current != null;
    }

    // Returns the data of the current node, or null if current is null
    public Double getCurrent() {
        return (current != null) ? current.data : null;
    }

    // Modifies the data of the current node
    public void setCurrent(double newData) {
        if (current != null) current.data = newData;
    }

    // Adds a new node with the given data to the end of the list
    public void add(double data) {
        Node newNode = new Node(data);
        if (head == null) { // If the list is empty, set head and tail
            head = tail = newNode;
        } else { // Otherwise, attach to the tail and update tail reference
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Adds a new node after the current position
    public void addAfterCurrent(double data) {
        if (current == null) {
            System.out.println("Error: Cannot add after null current position.");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        if (current == tail) { // If adding at the end, update the tail
            tail = newNode;
        }
    }

    // Removes the first occurrence of the specified data from the list
    public void remove(double data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                if (temp.next != null) temp.next.prev = temp.prev;
                if (temp.prev != null) temp.prev.next = temp.next;

                // If current is removed, move to next or prev node
                if (current == temp) {
                    current = (temp.next != null) ? temp.next : temp.prev;
                }
                return; // Exit after deleting first match
            }
            temp = temp.next;
        }
    }

    // Removes the node at the current position and updates current reference
    public void removeCurrent() {
        if (current == null) return;
        if (current == head) head = current.next;
        if (current == tail) tail = current.prev;
        if (current.next != null) current.next.prev = current.prev;
        if (current.prev != null) current.prev.next = current.next;

        // Update current to next node if possible, otherwise to prev or null
        current = (current.next != null) ? current.next : current.prev;
    }

    // Returns true if the list contains the specified data
    public boolean contains(double data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Prints all elements in the list
    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
