package unit2.com.ecommerce;

import unit2.com.ecommerce.models.Customer;
import unit2.com.ecommerce.models.Product;
import unit2.com.ecommerce.orders.Order;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class ECommerceSystem {
    private static final Logger logger = Logger.getLogger(ECommerceSystem.class.getName());

    // Color codes
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create available products
        Product product1 = new Product(1, "Laptop", 1200.99);
        Product product2 = new Product(2, "Smartphone", 799.49);
        Product product3 = new Product(3, "Tablet", 499.99);
        Product product4 = new Product(4, "Headphones", 199.99);

        List<Product> availableProducts = List.of(product1, product2, product3, product4);

        // Welcome banner
        System.out.println(GREEN + "==============================");
        System.out.println("Welcome to GameStop!");
        System.out.println("==============================\n" + RESET);
        System.out.println("We're the world's largest retail gaming & trade-in destination for games, systems, consoles & accessories.");

        // Customer input
        System.out.print("Please enter your name to continue with your order: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(101, customerName);

        // Personalized greeting
        System.out.println(GREEN + "\nHey " + customerName + "! What do you want to buy?\n" + RESET);

        boolean continueShopping = true;

        while (continueShopping) {
            System.out.println("Available Products:");
            System.out.println("+----+----------------------+-----------+");
            System.out.println("| ID | Product Name          | Price     |");
            System.out.println("+----+----------------------+-----------+");
            for (Product product : availableProducts) {
                System.out.printf("| %-2d | %-20s | $%-8.2f |\n", product.getProductID(), product.getName(), product.getPrice());
            }
            System.out.println("+----+----------------------+-----------+");


            try {
                System.out.print("Enter the product number to add to cart (or 0 to finish): ");
                int productSelection = scanner.nextInt();

                if (productSelection == 0) {
                    continueShopping = false;
                } else if (productSelection < 1 || productSelection > availableProducts.size()) {
                    throw new IllegalArgumentException(RED + "Invalid product selection: " + productSelection + RESET);
                } else {
                    customer.addToCart(availableProducts.get(productSelection - 1));
                    customer.showCart();  // Show cart after adding a product
                }
            } catch (InputMismatchException e) {
                logger.log(Level.SEVERE, "Invalid input. Please enter a valid number.", e);
                scanner.next(); // Clear the invalid input
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, e.getMessage());
                System.out.println(e.getMessage());
            }
        }

        // Confirm order placement
        System.out.print("\nDo you want to place the order? (yes/no): ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("yes")) {
            try {
                System.out.print("Placing order");
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(200); // Simulate order processing
                    System.out.print(".");
                }
                List<Product> orderProducts = new ArrayList<>(customer.getCart());
                if (orderProducts.isEmpty()) {
                    throw new IllegalStateException(RED + "Cannot place an order with an empty cart." + RESET);
                }
                Order order = new Order(1001, customer, orderProducts);
                order.displayOrderSummary();
                System.out.println(GREEN + "Order placed successfully!" + RESET);
            } catch (IllegalStateException | InterruptedException e) {
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(RED + "Order was not placed." + RESET);
        }

        scanner.close();
    }
}
