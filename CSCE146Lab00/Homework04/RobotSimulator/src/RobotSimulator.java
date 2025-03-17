//Justin Schlag 2025

import java.io.*;
import java.util.*;

// Custom generic queue implementation
class Queue<T> {
    private LinkedList<T> list = new LinkedList<>();
    
    public void enqueue(T item) {
        list.addLast(item);
    }
    
    public T dequeue() {
        return list.isEmpty() ? null : list.removeFirst();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

// Robot simulator class
public class RobotSimulator {
    private static final char EMPTY = '_';
    private static final char OBSTACLE = 'X';
    private static final char ROBOT = 'O';
    private static char[][] board;
    private static int robotX = 0, robotY = 0;
    private static Queue<String> commandQueue = new Queue<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Robot Simulator");
        
        while (true) {
            // User input for file names
            System.out.println("Enter file for the Board");
            String boardFile = scanner.nextLine();
            System.out.println("Enter file for the Robot Commands");
            String commandFile = scanner.nextLine();
            
            if (!loadBoard(boardFile) || !loadCommands(commandFile)) {
                System.out.println("Error loading files. Please try again.");
                continue;
            }
            
            System.out.println("\nSimulation begin");
            simulate();
            
            System.out.println("\nSimulation end");
            System.out.println("Quit? Enter 'true' to quit or hit enter to run another simulation");
            if (scanner.nextLine().equalsIgnoreCase("true")) break;
        }
        scanner.close();
    }

    // Loads the board configuration from file
    private static boolean loadBoard(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<char[]> tempBoard = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                tempBoard.add(line.toCharArray());
            }
            board = tempBoard.toArray(new char[0][]);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    // Reads robot commands from file and enqueues valid ones
    private static boolean loadCommands(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidCommand(line)) {
                    commandQueue.enqueue(line);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    // Executes robot movement commands
    private static void simulate() {
        int commandCount = 0;
        while (!commandQueue.isEmpty()) {
            String command = commandQueue.dequeue();
            System.out.println("\nCommand " + commandCount++);
            
            if (!moveRobot(command)) {
                System.out.println("CRASH!");
                break;
            }
            printBoard();
        }
    }
    
    // Moves the robot according to the command
    private static boolean moveRobot(String command) {
        int newX = robotX, newY = robotY;
        switch (command) {
            case "Move Up": newX--; break;
            case "Move Down": newX++; break;
            case "Move Left": newY--; break;
            case "Move Right": newY++; break;
            default: return true;
        }
        
        if (isOutOfBounds(newX, newY) || board[newX][newY] == OBSTACLE) {
            return false; // Collision or out of bounds
        }
        
        board[robotX][robotY] = EMPTY; // Clear old position
        robotX = newX;
        robotY = newY;
        board[robotX][robotY] = ROBOT; // Move robot
        return true;
    }
    
    // Validates the given command
    private static boolean isValidCommand(String command) {
        return command.equals("Move Up") || command.equals("Move Down") ||
               command.equals("Move Left") || command.equals("Move Right");
    }
    
    // Checks if a position is outside the board boundaries
    private static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
    
    // Displays the current state of the board
    private static void printBoard() {
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }
}
