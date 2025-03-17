// Justin Schlag 2025
// Video Game Database - Homework 02 CSCE146

import java.io.*;
import java.util.*;

// Class representing a single video game
class Game {
    String title;
    String console;

    public Game(String title, String console) {
        this.title = title;
        this.console = console;
    }

    @Override
    public String toString() {
        return title + "\t" + console;
    }
}

// Node class for the linked list
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Generic Linked List Implementation
class GenericLinkedList<T> {
    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    //Clears linked list
    public void clear() {
        head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public GenericLinkedList<T> search(String title, String console) {
        GenericLinkedList<T> results = new GenericLinkedList<>();
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data instanceof Game game) {
                if ((title.equals("*") || game.title.toLowerCase().contains(title.toLowerCase())) &&
                    (console.equals("*") || game.console.toLowerCase().contains(console.toLowerCase()))) {
                    results.add(temp.data);
                }
            }
            temp = temp.next;
        }
        return results;
    }

    //Displays elements in the linked list
    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class GameDatabase {
    private final GenericLinkedList<Game> gameList = new GenericLinkedList<>();
    private GenericLinkedList<Game> lastSearchResults = new GenericLinkedList<>();
    private boolean isLoaded = false;

    //Loads games from a file into the linked list
    public void loadFromFile(String filename) {
        gameList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    gameList.add(new Game(parts[0].trim(), parts[1].trim()));
                }
            }
            isLoaded = true;
            System.out.println("\nGames loaded successfully from " + filename + ".\n");
        } catch (IOException e) {
            System.out.println("\nError loading file: " + e.getMessage() + "\n");
        }
    }

    public void searchGames(String title, String console) {
        if (!isLoaded) {
            System.out.println("\nError: No game collection loaded. Load a file first.\n");
            return;
        }
        lastSearchResults = gameList.search(title, console);
        if (lastSearchResults.isEmpty()) {
            System.out.println("\nNo matching games found.\n");
        } else {
            System.out.println("\nSearch Results:\n");
            lastSearchResults.display();
            System.out.println();
        }
    }
    //Results to the file
    public void printSearchResultsToFile(String filename, boolean append) {
        if (!isLoaded) {
            System.out.println("\nError: No game collection loaded. Load a file first.\n");
            return;
        }
        if (lastSearchResults.isEmpty()) {
            System.out.println("\nNo search results to save.\n");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append))) {
            Node<Game> temp = lastSearchResults.getHead();
            while (temp != null) {
                bw.write(temp.data.toString());
                bw.newLine();
                temp = temp.next;
            }
            System.out.println("\nResults saved to " + filename + "\n");
        } catch (IOException e) {
            System.out.println("\nError writing to file: " + e.getMessage() + "\n");
        }
    }

    public void displayGames() {
        if (!isLoaded) {
            System.out.println("\nError: No game collection loaded. Load a file first.\n");
            return;
        }
        System.out.println("\nCurrent Games in Database:\n");
        gameList.display();
        System.out.println();
    }
}
//What the user sees, choices and input
public class VideoGameDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameDatabase database = new GameDatabase();
        boolean firstRun = true;

        while (true) {
            if (firstRun) {
                System.out.println("\nWelcome to the Video Game Database!\n");
                firstRun = false;
            }
            System.out.println("Enter 1 to load the video game database");
            System.out.println("Enter 2 to search the database");
            System.out.println("Enter 3 to print current results to the console");
            System.out.println("Enter 4 to print current results to file");
            System.out.println("Enter 0 to quit\n");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the file name: ");
                    String filename = scanner.nextLine().trim();
                    database.loadFromFile(filename);
                    break;
                case 2:
                    System.out.print("\nEnter the name of the game or '*' for all: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("\nEnter the name of the console or '*' for all: ");
                    String console = scanner.nextLine().trim();
                    database.searchGames(title, console);
                    break;
                case 3:
                    database.displayGames();
                    break;
                case 4:
                    System.out.print("\nEnter the file name to save results: ");
                    String saveFile = scanner.nextLine().trim();
                    System.out.print("\nWould you like to append? (true/false): ");
                    boolean append;
                    while (true) {
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.equals("true")) {
                            append = true;
                            break;
                        } else if (input.equals("false")) {
                            append = false;
                            break;
                        } else {
                            System.out.print("Invalid input. Please enter 'true' or 'false': ");
                        }
                    }
                    database.printSearchResultsToFile(saveFile, append);
                    break;
                case 0:
                    System.out.println("Goodbye!\n");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
    }
}
