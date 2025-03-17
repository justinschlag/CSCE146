//Justin Schlag 2025
// GroceryItem.java

public class GroceryItem {
    // Instance variables
    private String name;
    private double value;

    // Default constructor
    public GroceryItem() {
        this.name = "none";
        this.value = 0.0;
    }

    // Parameterized constructor
    public GroceryItem(String name, double value) {
        setName(name);
        setValue(value);
    }

    // Accessor for name
    public String getName() {
        return name;
    }

    // Mutator for name
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "none";
        }
    }

    // Accessor for value
    public double getValue() {
        return value;
    }

    // Mutator for value
    public void setValue(double value) {
        if (value >= 0) {
            this.value = value;
        } else {
            this.value = 0.0;
        }
    }

    // toString method
    @Override
    public String toString() {
        return "Grocery Item Name: " + name + " Value: " + value;
    }

    // Equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GroceryItem that = (GroceryItem) obj;
        return Double.compare(that.value, value) == 0 && name.equals(that.name);
    }
}
