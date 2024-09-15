package unit2.com.ecommerce.orders;

import unit2.com.ecommerce.models.Customer;
import unit2.com.ecommerce.models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;

    public Order(int orderID, Customer customer, List<Product> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.orderTotal = calculateOrderTotal();
    }

    // Calculate total order cost
    private double calculateOrderTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Generate order summary with grouped products
    public void displayOrderSummary() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order Details:");

        Map<Product, Integer> productCountMap = new HashMap<>();
        for (Product product : products) {
            productCountMap.put(product, productCountMap.getOrDefault(product, 0) + 1);
        }

        for (Map.Entry<Product, Integer> entry : productCountMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("Product ID: %d, Name: %s, Price: $%.2f, Qty: %d\n",
                    product.getProductID(), product.getName(), product.getPrice(), quantity);
        }

        System.out.printf("Total Cost: $%.2f\n", orderTotal);
    }
}
