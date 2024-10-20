package unit6.app;

import unit6.catalog.Catalog;
import unit6.models.Book;
import unit6.models.DVD;
import unit6.models.LibraryItem;
import unit6.models.Magazine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryCatalogApp {
    public static void main(String[] args) {
        Catalog<LibraryItem> catalog = new Catalog<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("\nLibrary Catalog Menu:");
                System.out.println("1. Add a new item");
                System.out.println("2. Remove an item");
                System.out.println("3. View catalog");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addItem(catalog, scanner);
                        break;
                    case 2:
                        removeItem(catalog, scanner);
                        break;
                    case 3:
                        catalog.displayItems();
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void addItem(Catalog<LibraryItem> catalog, Scanner scanner) {
        try {
            String type;
            while (true) {
                System.out.print("Enter item type (book/dvd/magazine): ");
                type = scanner.nextLine().toLowerCase();

                // Validate the item type
                if (type.equals("book") || type.equals("dvd") || type.equals("magazine")) {
                    break;  // Valid type, exit the loop
                } else {
                    System.out.println("Invalid item type. Please enter 'book', 'dvd', or 'magazine'.");
                }
            }

            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter itemID: ");
            String itemID = scanner.nextLine();

            if (type.equals("book")) {
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();
                Book book = new Book(title, author, itemID, genre);
                catalog.addItem(book);
            } else if (type.equals("dvd")) {
                System.out.print("Enter director: ");
                String director = scanner.nextLine();
                DVD dvd = new DVD(title, author, itemID, director);
                catalog.addItem(dvd);
            } else if (type.equals("magazine")) {
                System.out.print("Enter issue: ");
                String issue = scanner.nextLine();
                Magazine magazine = new Magazine(title, author, itemID, issue);
                catalog.addItem(magazine);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter the correct format.");
            scanner.nextLine(); // Clear the invalid input
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeItem(Catalog<LibraryItem> catalog, Scanner scanner) {
        try {
            System.out.print("Enter itemID to remove: ");
            String removeID = scanner.nextLine();
            catalog.removeItem(removeID);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid itemID.");
            scanner.nextLine(); // Clear the invalid input
        }
    }
}
