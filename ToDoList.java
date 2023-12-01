import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private static ArrayList<String> toDoItems = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    viewItems();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n----- To-Do List Menu -----");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. View Items");
        System.out.println("4. Exit");
    }

    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        toDoItems.add(item);
        System.out.println("Item added: " + item);
    }

    private static void removeItem() {
        System.out.print("Enter the index of the item to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (index >= 0 && index < toDoItems.size()) {
            String removedItem = toDoItems.remove(index);
            System.out.println("Item removed: " + removedItem);
        } else {
            System.out.println("Invalid index. No item removed.");
        }
    }

    private static void viewItems() {
        System.out.println("\n----- To-Do List Items -----");
        if (toDoItems.isEmpty()) {
            System.out.println("No items in the to-do list.");
        } else {
            for (int i = 0; i < toDoItems.size(); i++) {
                System.out.println(i + 1 + ". " + toDoItems.get(i));
            }
        }
    }
}
