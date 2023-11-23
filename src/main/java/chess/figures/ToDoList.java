package chess.figures;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("----- To-Do List -----");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Complete");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    markComplete(scanner);
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task: ");
        String task = scanner.next();
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    private static void markComplete(Scanner scanner) {
        System.out.print("Enter the index of the task to mark as complete: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < tasks.size()) {
            String completedTask = tasks.get(index);
            tasks.remove(index);
            System.out.println("Task marked as complete: " + completedTask);
        } else {
            System.out.println("Invalid index. Please enter a valid index.");
        }
    }

    private static void viewTasks() {
        System.out.println("----- Tasks -----");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i));
            }
        }
    }
}

