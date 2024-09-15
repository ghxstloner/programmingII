package unit2.com.ecommerce.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private int customerID;
    private String name;
    private List<Product> cart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.cart = new ArrayList<>();
    }

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    // Method to add product to cart
    public void addToCart(Product product) {
        cart.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    // Method to calculate total cost
    public double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }

    // Display customer cart details with grouping and formatted prices
    public void showCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        Map<Product, Integer> productCountMap = new HashMap<>();
        for (Product product : cart) {
            productCountMap.put(product, productCountMap.getOrDefault(product, 0) + 1);
        }

        System.out.println("Customer: " + name + "'s Cart:");
        for (Map.Entry<Product, Integer> entry : productCountMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("Product ID: %d, Name: %s, Price: $%.2f, Qty: %d\n",
                    product.getProductID(), product.getName(), product.getPrice(), quantity);
        }
        System.out.printf("Total Cost: $%.2f\n", calculateTotal());
    }
}
