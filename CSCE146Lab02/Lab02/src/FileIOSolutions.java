//  Justin Schlag 2025
//  FileIOSolutions.java

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOSolutions {

    /**
     * Reads a file word-by-word, replaces "is" with "was" (case-insensitive),
     * and writes the result to a new output file. Also prints each word to the console.
     *
     * @param inputFileName  The name of the input file to read from.
     * @param outputFileName The name of the output file to write to.
     */
    public static void pastTense(String inputFileName, String outputFileName) {
        Scanner fileScanner = null;
        FileWriter writer = null;

        try {
            fileScanner = new Scanner(new File(inputFileName));
            writer = new FileWriter(outputFileName);

            // Read and process words
            while (fileScanner.hasNext()) {
                String word = fileScanner.next();

                // Replace "is" with "was", ignoring case
                if (word.equalsIgnoreCase("is")) {
                    word = "was";
                }

                // Print to console and write to file
                System.out.println(word);
                writer.write(word + "\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred with file I/O: " + e.getMessage());
        } finally {
            try {
                if (fileScanner != null) fileScanner.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing resources.");
            }
        }
    }

    /**
     * Reads a tab-delimited file containing tube data and calculates the total volume.
     * @param inputFileName The name of the file containing tube data.
     * @return The total combined volume of all tubes in the file.
     */
    public static double totalTubeVolume(String inputFileName) {
        double totalVolume = 0.0;
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(inputFileName));

            // Read each line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");

                // Ensure the line has valid data (id, radius, height)
                if (parts.length == 3) {
                    try {
                        double radius = Double.parseDouble(parts[1]);
                        double height = Double.parseDouble(parts[2]);

                        // Calculate volume and add to total
                        double volume = Math.PI * radius * radius * height;
                        totalVolume += volume;
                    } catch (NumberFormatException e) {
                        // Ignore invalid lines
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("File not found: " + inputFileName);
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        return totalVolume;
    }
}
