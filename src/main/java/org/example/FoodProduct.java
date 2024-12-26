package org.example;

public class FoodProduct extends Product implements Discountable {

    public FoodProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public double applyDiscount(double total) {
        // Food gets a 5% discount
        return total - (total * 0.05);
    }

}
