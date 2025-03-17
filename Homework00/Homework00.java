//Justin Schlag 2025

import java.util.Scanner;

public class Homework00 {

    // Helper method to read vector values from the user
    private static double[] readVector(Scanner scanner, int size, String vectorName) {
        double[] vector = new double[size];
        System.out.println("Enter values for " + vectorName);
        for (int i = 0; i < size; i++) {
            vector[i] = getValidDouble(scanner);
        }
        return vector;
    }

    // Method to add two vectors
    private static double[] addVectors(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }
        return result;
    }

    // Method to subtract two vectors
    private static double[] subtractVectors(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return result;
    }

    // Method to calculate the magnitude of a vector
    private static double calculateMagnitude(double[] vector) {
        double sumOfSquares = 0;
        for (double component : vector) {
            sumOfSquares += component * component;
        }
        return Math.sqrt(sumOfSquares);
    }

    // Helper method to display a vector vertically (each element on a new line)
    private static void displayVector(double[] vector) {
        for (double value : vector) {
            System.out.printf("%.1f%n", value);
        }
    }

    // Helper method to get a valid integer input
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Helper method to get a valid double input
    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Main menu method
    private static void displayMenu() {
        System.out.println("Enter 1. To Add 2 Vectors");
        System.out.println("Enter 2. To Subtract 2 Vectors");
        System.out.println("Enter 3. To Find the Magnitude of a Vector");
        System.out.println("Enter 9. To Quit");
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Vector Operations Program!");

        while (true) {
            displayMenu();
            int choice = getValidInt(scanner);

            switch (choice) {
                case 1 -> {
                    // Add vectors
                    System.out.println("Enter the size of the Vectors");
                    int sizeAdd = getValidInt(scanner);
                    if (sizeAdd < 1) {
                        System.out.println("Invalid Size");
                        continue;
                    }

                    double[] vector1Add = readVector(scanner, sizeAdd, "Vector1");
                    double[] vector2Add = readVector(scanner, sizeAdd, "Vector2");

                    double[] resultAdd = addVectors(vector1Add, vector2Add);

                    System.out.println("Result:");
                    displayVector(vector1Add);
                    System.out.println("+");
                    displayVector(vector2Add);
                    System.out.println("=");
                    displayVector(resultAdd);
                }

                case 2 -> {
                    // Subtract vectors
                    System.out.println("Enter the size of the Vectors");
                    int sizeSub = getValidInt(scanner);
                    if (sizeSub < 1) {
                        System.out.println("Invalid Size");
                        continue;
                    }

                    double[] vector1Sub = readVector(scanner, sizeSub, "Vector1");
                    double[] vector2Sub = readVector(scanner, sizeSub, "Vector2");

                    double[] resultSub = subtractVectors(vector1Sub, vector2Sub);

                    System.out.println("Result:");
                    displayVector(vector1Sub);
                    System.out.println("-");
                    displayVector(vector2Sub);
                    System.out.println("=");
                    displayVector(resultSub);
                }

                case 3 -> {
                    // Find magnitude
                    System.out.println("Enter the size of the Vector");
                    int sizeMag = getValidInt(scanner);
                    if (sizeMag < 1) {
                        System.out.println("Invalid Size");
                        continue;
                    }

                    double[] vectorMag = readVector(scanner, sizeMag, "the Vector");
                    double magnitude = calculateMagnitude(vectorMag);

                    System.out.printf("The magnitude is: %.6f%n", magnitude);
                }

                case 9 -> {
                    // Quit
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
