package org.example;

public class Product implements Discountable {

        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
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
    public double applyDiscount(double discount) {
        price -= price * discount; // Applying the discount to the price directly
        return price; // Return the updated price after discount
    }
}
