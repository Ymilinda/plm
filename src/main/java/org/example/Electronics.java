package org.example;

public class Electronics extends Product implements Discountable {

    public Electronics(String name, double price) {
        super(name, price);
    }

    @Override
    public double applyDiscount(double discount) {
        // Electronics gets a 10% discount
        //double discount = 0.1;
        price -= price * 0.1;
        return price;
    }


    @Override
    public String toString() {
        return "Electronics{" +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }


}
