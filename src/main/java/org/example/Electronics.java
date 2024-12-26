package org.example;

public class Electronics extends Product implements Discountable {

    public Electronics(String name, double price) {
        super(name, price);
    }

    // Implement the applyDiscount method
    @Override
    public double applyDiscount(double total) {
        return total - (total * 0.10);
    }

    @Override
    public String toString() {
        return "Electronics{" +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }


}
