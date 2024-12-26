package org.example;

import java.util.Random;
import java.util.Scanner;

public class ManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a new product
        Product laptop = new Product("Laptop", 1000);
        Product mouse = new Product("Mouse", 20);
        Product keyboard = new Product("Keyboard", 50);

        // Create a new cart
        Cart cart = new Cart(new Product[]
                {laptop, mouse, keyboard}
        );

        // Create a new customer
        Customer customer = new Customer("John Doe", "123 Main St");

        // Print the cart
        System.out.println(cart);

        //display options
        while (true) {
            System.out.println("1. Add product to cart");
            System.out.println("2. Remove product from cart");
            System.out.println("3. View cart");
            System.out.println("4. Exit");

            // Get the user's choice
            int choice = scanner.nextInt();

            // Add product to cart
            if (choice == 1) {
                System.out.println("Enter the product name: ");
                String name = scanner.next();
                System.out.println("Enter the product price: ");
                double price = scanner.nextDouble();
                Product product = new Product(name, price);
                cart.addProduct(product);

                // Add discount to product
                if (product instanceof Discountable) {
                    System.out.println("Enter the discount: ");
                    double discount = scanner.nextDouble();
                    product.applyDiscount(discount);
                }
            }

            // Remove product from cart
            if (choice == 2) {
                System.out.println("Enter the product name: ");
                String name = scanner.next();
                cart.removeProduct(name);
            }

            // View cart
            if (choice == 3) {
                System.out.println(cart);
            }

            // Exit
            if (choice == 4) {
                break;
            }

        }


    }
}