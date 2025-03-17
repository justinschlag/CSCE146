// Justin Schlag 2025
// Defines the Process class representing a process in the scheduler.

public class Process {
    private String name;
    private double completionTime;

    // Default Constructor
    public Process() {
        this.name = "none";
        this.completionTime = 0.0;
    }

    // Parameterized Constructor
    public Process(String name, double completionTime) {
        this.name = (name != null && !name.isEmpty()) ? name : "none";
        this.completionTime = (completionTime >= 0) ? roundToPrecision(completionTime) : 0.0;
    }

    // Accessor for name
    public String getName() {
        return name;
    }

    // Mutator for name
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "none";
        }
    }

    // Accessor for completionTime
    public double getCompletionTime() {
        return completionTime;
    }

    // Mutator for completionTime
    public void setCompletionTime(double completionTime) {
        if (completionTime >= 0) {
            this.completionTime = roundToPrecision(completionTime);
        } else {
            this.completionTime = 0.0;
        }
    }

    // Method to round numbers to match the precision of the initial completion time
    private double roundToPrecision(double value) {
        return Double.parseDouble(String.format("%.15f", value));
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format("Process Name: %s Completion Time: %.15f", name, completionTime);
    }
}
