package com.mycompany.dsa_project;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Product {
    String name;
    int quantity;
    double price;

    Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class StockOrder {
    int orderID;
    String productName;
    int quantity;
    String supplier;
    boolean received;

    StockOrder(int id, String name, int quantity, String supplier) {
        this.orderID = id;
        this.productName = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.received = false;
    }
}

public class DSA_project {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<StockOrder> orders = new ArrayList<>();

    static int nextOrderID = 1;

    public static void main(String[] args) {

        // Initial inventory
        products.add(new Product("Shirts", 10, 2000));
        products.add(new Product("Pants", 15, 3000));
        products.add(new Product("Dresses", 20, 4000));

        int choice;

        do {
            System.out.println("\n===== CLOTHING INVENTORY SYSTEM =====");
            System.out.println("1. View Total Stock");
            System.out.println("2. View Individual Product Stock");
            System.out.println("3. View Total Stock Value");
            System.out.println("4. Place Stock Order");
            System.out.println("5. View Pending Orders");
            System.out.println("6. Receive Stock Order");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:
                    viewTotalStock();
                    break;

                case 2:
                    viewProductStock();
                    break;

                case 3:
                    calculateStockValue();
                    break;

                case 4:
                    placeOrder();
                    break;

                case 5:
                    viewPendingOrders();
                    break;

                case 6:
                    receiveOrder();
                    break;

                case 7:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }

    // View total quantity of all products
    public static void viewTotalStock() {

        int total = 0;

        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).quantity;
        }

        System.out.println("Total Stock: " + total);
    }

    // View individual product stock
    public static void viewProductStock() {

        System.out.print("Enter product name: ");
        String name = input.next();

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).name.equalsIgnoreCase(name)) {

                System.out.println("Product: " + products.get(i).name);
                System.out.println("Quantity: " + products.get(i).quantity);
                System.out.println("Price: " + products.get(i).price);
                return;
            }
        }

        System.out.println("Product not found!");
    }

    // Calculate total stock value
    public static void calculateStockValue() {

        double total = 0;

        for (int i = 0; i < products.size(); i++) {

            total += products.get(i).quantity * products.get(i).price;
        }

        System.out.println("Total Stock Value: Rs. " + total);
    }

    // Place a new stock order
    public static void placeOrder() {

        System.out.print("Enter product name: ");
        String name = input.next();

        boolean found = false;

        for (int i = 0; i < products.size(); i++) {

            if (products.get(i).name.equalsIgnoreCase(name)) {
                found = true;
                break;
            }
        }

        if (found == false) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter order quantity: ");
        int quantity = input.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        System.out.print("Enter supplier name: ");
        String supplier = input.next();

        StockOrder order = new StockOrder(
                nextOrderID, name, quantity, supplier);

        orders.add(order);

        System.out.println("Order placed successfully!");
        System.out.println("Order ID: " + nextOrderID);

        nextOrderID++;
    }

    // Display pending orders
    public static void viewPendingOrders() {

        if (orders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        for (int i = 0; i < orders.size(); i++) {

            System.out.println("\nOrder ID: " + orders.get(i).orderID);
            System.out.println("Product: " + orders.get(i).productName);
            System.out.println("Quantity: " + orders.get(i).quantity);
            System.out.println("Supplier: " + orders.get(i).supplier);
            System.out.println("Status: Pending");
        }
    }

    // Receive an order and update inventory
    public static void receiveOrder() {

        System.out.print("Enter Order ID: ");
        int id = input.nextInt();

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).orderID == id) {

                if (orders.get(i).received == false) {

                    for (int j = 0; j < products.size(); j++) {

                        if (products.get(j).name.equalsIgnoreCase(
                                orders.get(i).productName)) {

                            // Add ordered quantity to existing stock
                            products.get(j).quantity += orders.get(i).quantity;

                            // Mark order as received
                            orders.get(i).received = true;

                            System.out.println("Order received successfully!");
                            System.out.println("Updated " +
                                    products.get(j).name + " quantity: " +
                                    products.get(j).quantity);

                            // Remove order from pending list
                            orders.remove(i);

                            return;
                        }
                    }

                    System.out.println("Product not found!");
                    return;
                }
            }
        }

        System.out.println("Pending order ID not found!");
    }
}
