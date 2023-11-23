package chess.figures;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("----- Address Book -----");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter the name: ");
        String name = scanner.next();
        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.next();

        Contact newContact = new Contact(name, phoneNumber);
        contacts.add(newContact);

        System.out.println("Contact added: " + newContact);
    }

    private static void viewContacts() {
        System.out.println("----- Contacts -----");
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter the name to search: ");
        String searchName = scanner.next();

        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Contact found: " + contact);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found for the name: " + searchName);
        }
    }
}

