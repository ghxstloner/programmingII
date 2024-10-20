package unit6.catalog;

import unit6.models.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class Catalog<T extends LibraryItem> {
    private List<T> items;

    public Catalog() {
        items = new ArrayList<>();
    }

    // Method to add a new item to the catalog
    public void addItem(T item) {
        items.add(item);
        System.out.println(item.getTitle() + " added to the catalog.");
    }

    // Method to remove an item from the catalog by itemID
    public void removeItem(String itemID) {
        T itemToRemove = null;
        for (T item : items) {
            if (item.getItemID().equals(itemID)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            System.out.println(itemToRemove.getTitle() + " removed from the catalog.");
        } else {
            System.out.println("Item with ID " + itemID + " not found.");
        }
    }

    // Method to display all items in the catalog
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            System.out.println("Catalog Items:");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }
}
