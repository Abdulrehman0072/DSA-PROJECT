package com.mycompany.dsa_project;
import java.util.List;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        
    }
}
