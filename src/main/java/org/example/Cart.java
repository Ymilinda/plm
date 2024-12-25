package org.example;

public class Cart {
    // Add the fields
    private Product[] products;

    // Add the constructor
    public Cart(Product[] products) {
        this.products = products;
    }

    // Add the getter and setter methods
    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    // Add the getTotalPrice method
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    // Add the toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append(product.getName()).append(" - $").append(product.getPrice()).append("\n");
        }
        sb.append("Total price: $").append(getTotalPrice());
        return sb.toString();
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
        Cart cart = (Cart) obj;
        return java.util.Arrays.equals(products, cart.products);
    }

    public void addProduct(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        newProducts[products.length] = product;
        products = newProducts;
    }

    public void removeProduct(String name) {
        Product[] newProducts = new Product[products.length - 1];
        int j = 0;
        for (Product product : products) {
            if (!product.getName().equals(name)) {
                newProducts[j] = product;
                j++;
            }
        }
        products = newProducts;
    }


}
