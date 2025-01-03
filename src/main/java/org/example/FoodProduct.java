package org.example;

public class FoodProduct extends Product implements Discountable {

    public FoodProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public double applyDiscount(double discount) {
        // Food gets a 5% discount
        //double discount = 0.05;
        price -= price * 0.05;
        return price;
    }

    // Add the toString method
    @Override
    public String toString() {
        return "FoodProduct{" +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }

    // Add the equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FoodProduct that = (FoodProduct) obj;
        return Double.compare(that.getPrice(), getPrice()) == 0 && getName().equals(that.getName());
    }



}
