// Justin Schlag 2025
// Homework 01 Showcase Showdown!

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class ShowcaseShowdown {
    private static final int SHOWCASE_SIZE = 5; // Number of prizes per round
    private static final double WINNING_MARGIN = 1300.0; // Margin for error for winning

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String filename = "ExamplePrizeFile.txt"; // Use provided file name

        // Read prizes from file
        String[] prizeNames = new String[100]; // Assuming max 100 prizes
        double[] prizePrices = new double[100];
        int prizeCount = readPrizesFromFile(filename, prizeNames, prizePrices);

        if (prizeCount == 0) {
            System.out.println("No valid prizes found. Exiting game.");
            inputScanner.close();
            return;
        }

        System.out.println("Welcome to the showcase showdown!");

        boolean keepPlaying = true;
        while (keepPlaying) {
            // Select unique prizes for the showcase
            int[] selectedIndices = getRandomPrizes(prizeCount);

            // Display selected prizes
            System.out.println("\nYour prizes are:");
            double totalCost = 0.0;
            for (int index : selectedIndices) {
                System.out.println(prizeNames[index]);
                totalCost += prizePrices[index];
            }

            // Get user's guess
            System.out.println("\nYou must guess the total cost of the prizes without going over and within $" + WINNING_MARGIN + " of its actual price.");
            System.out.print("Enter your guess: ");
            double userGuess = inputScanner.nextDouble();

            // Determine if the user wins
            System.out.printf("\nThe actual cost was %.2f\n", totalCost);
            if (userGuess > totalCost) {
                System.out.println("Your guess was over. You lose.");
            } else if (userGuess >= (totalCost - WINNING_MARGIN)) {
                System.out.println("You win!!!");
            } else {
                System.out.println("Your guess was close, but not close enough. You lose.");
            }

            // Ask if user wants to play again
            System.out.print("\nWould you like to quit? Enter \"yes\" to quit: ");
            inputScanner.nextLine(); // Consume newline
            String response = inputScanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                keepPlaying = false;
            }
        }

        System.out.println("Goodbye!");
        inputScanner.close();
    }

    /**
     * Reads prizes from a file and stores them in arrays.
     * The file should be tab-delimited with each line containing a name and a price.
     *
     * @param filename     The name of the prize file.
     * @param prizeNames   Array to store prize names.
     * @param prizePrices  Array to store prize prices.
     * @return The number of valid prizes read.
     */
    private static int readPrizesFromFile(String filename, String[] prizeNames, double[] prizePrices) {
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(new File(filename));

            while (fileScanner.hasNextLine() && count < prizeNames.length) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");

                if (parts.length != 2) continue; // Skip invalid lines

                try {
                    prizeNames[count] = parts[0]; // Prize name
                    prizePrices[count] = Double.parseDouble(parts[1]); // Prize price
                    count++;
                } catch (NumberFormatException e) {
                    // Ignore lines where the price is not a valid number
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Prize file not found.");
        }

        return count;
    }

    /**
     * Selects five unique random prize indices.
     *
     * @param maxIndex The total number of available prizes.
     * @return An array of five unique random indices.
     */
    private static int[] getRandomPrizes(int maxIndex) {
        Random rand = new Random();
        int[] selectedIndices = new int[SHOWCASE_SIZE];
        boolean[] usedIndices = new boolean[maxIndex];

        for (int i = 0; i < SHOWCASE_SIZE; i++) {
            int randIndex;
            do {
                randIndex = rand.nextInt(maxIndex);
            } while (usedIndices[randIndex]); // Ensure uniqueness

            selectedIndices[i] = randIndex;
            usedIndices[randIndex] = true;
        }

        return selectedIndices;
    }
}
