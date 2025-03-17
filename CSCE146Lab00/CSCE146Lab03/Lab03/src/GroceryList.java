//Justin Schlag 2025
// GroceryList.java

public class GroceryList {
    // Inner class representing a node in the linked list
    private class ListNode {
        GroceryItem data;
        ListNode link;

        // Default constructor
        public ListNode() {
            this.data = null;
            this.link = null;
        }

        // Parameterized constructor
        public ListNode(GroceryItem data, ListNode link) {
            this.data = data;
            this.link = link;
        }
    }

    // Instance variables for GroceryList
    private ListNode head;
    private ListNode current;
    private ListNode previous;

    // Default constructor initializes an empty list node
    public GroceryList() {
        this.head = new ListNode(); // Initialize head as an empty node
        this.current = head;
        this.previous = null;
    }

    // Moves the current node to the next node in the list
    public void gotoNext() {
        if (current != null) {
            previous = current;
            current = current.link;
        }
    }

    // Returns the data at the current node
    public GroceryItem getCurrent() {
        if (current != null) {
            return current.data;
        }
        return null;
    }

    // Sets the data of the current node
    public void setCurrent(GroceryItem item) {
        if (current != null && item != null) {
            current.data = item;
        }
    }

    // Adds a new item to the end of the list
    public void addItem(GroceryItem item) {
        if (item == null) return;

        ListNode newNode = new ListNode(item, null);
        if (head.data == null) { // If list is empty
            head = newNode;
            current = head;
        } else {
            ListNode temp = head;
            while (temp.link != null) {
                temp = temp.link;
            }
            temp.link = newNode;
        }
    }

    // Adds a new item after the current node
    public void addItemAfterCurrent(GroceryItem item) {
        if (current == null || item == null) return;

        ListNode newNode = new ListNode(item, current.link);
        current.link = newNode;
    }

    // Removes the current node from the list
    public void removeCurrent() {
        if (current == null || head == null) return;

        if (current == head) {
            head = head.link;
        } else {
            previous.link = current.link;
        }
        current = current.link;
    }

    // Prints the list
    public void showList() {
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null) {
                System.out.println(temp.data);
            }
            temp = temp.link;
        }
    }

    // Checks if the list contains a specific grocery item
    public boolean contains(GroceryItem item) {
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null && temp.data.equals(item)) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }

    // Computes the total cost of all items in the list
    public double totalCost() {
        double total = 0.0;
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null) {
                total += temp.data.getValue();
            }
            temp = temp.link;
        }
        return total;
    }
}
