package com.felipe.backendlab.javacore.oop;

public class ExampleSalesSystem {

    public static void main(String[] args) {
        System.out.println("*** Sales System ***");

        Product item1 = new Product("Cepillo", 23.2);
        Product item2 = new Product("Papel", 20.8);

        Order order1 = new Order();
        order1.addProducts(item1);
        order1.addProducts(item2);
        order1.showOrder();

        // Segunda orden
        Order order2 = new Order();
        order2.addProducts(new Product("Playera", 50.00));
        order2.addProducts(item1);
        order2.addProducts(item2);
        order2.showOrder();
    }
}

class Order {
    private final int idOrder;
    private int countProducts;
    private static final int MAX_PRODUCTS = 10;
    private static int countOrders;

    private Product[] products;

    public Order() {
        this.idOrder = ++Order.countOrders;
        this.products = new Product[Order.MAX_PRODUCTS];
    }

    public void addProducts(Product product) {
        if (countProducts < MAX_PRODUCTS) {
            this.products[this.countProducts++] = product;
        } else {
            System.out.println("Too many products");
        }
    }

    public double getTotalOrder() {
        double total = 0;
        for (int i = 0; i < this.countProducts; i++) {
            total += this.products[i].getPrice();
        }
        return total;
    }

    public void showOrder() {
        System.out.println("id orden: " + this.idOrder);
        System.out.println("\tTotal orden: $" + this.getTotalOrder());
        System.out.println("\tProductos de la orden: ");
        for (int i = 0; i < this.countProducts; i++) {
            System.out.println("\t\t" + this.products[i]);
        }
        System.out.println("\n");
    }
}

class Product {

    private static int countProducts = 0;
    private final int idProduct;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.idProduct = ++Product.countProducts;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + this.idProduct +
                ", name='" + this.name + '\'' +
                ", price=" + this.price +
                '}';
    }
}