# Supermarket Management System: A Java-based Shopping Cart Solution

The Supermarket Management System is a Java application that provides a flexible and extensible shopping cart implementation with support for different product types and discount management. It enables businesses to manage customer purchases, apply product-specific discounts, and handle inventory through a thread-safe cart operations interface.

The system implements a robust product hierarchy with support for electronics and food products, each with their own discount rules. It features a thread-safe shopping cart that maintains product quantities and prices, while supporting operations like adding/removing products and applying discounts. The system is containerized using Docker for easy deployment and runs as a Spring Boot application with configurable settings.

## Repository Structure
```
supermarket-system/
├── src/main/java/org/example/           # Core application source code
│   ├── Cart.java                        # Shopping cart implementation with thread-safe operations
│   ├── Product.java                     # Base product class defining common attributes
│   ├── Customer.java                    # Customer entity management
│   ├── Electronics.java                 # Electronics product type with 10% discount
│   ├── FoodProduct.java                 # Food product type with 5% discount
│   ├── Discountable.java               # Interface for discount functionality
│   └── ManagementSystem.java           # Main application entry point
├── src/main/resources/
│   └── application.yaml                 # Spring Boot configuration
├── Dockerfile                           # Container definition for deployment
└── pom.xml                             # Maven project configuration
```

## Usage Instructions
### Prerequisites
- Java Development Kit (JDK) 17 or later
- Maven 3.6 or later
- Docker (optional, for containerized deployment)
- Spring Boot 2.x compatible environment

### Installation

#### Local Development Setup
```bash
# Clone the repository
git clone <repository-url>
cd supermarket-system

# Build the project
mvn clean install

# Run the application
java -jar target/SuperMarketSystem-Christmas.jar
```

#### Docker Deployment
```bash
# Build Docker image
docker build -t supermarket-system .

# Run container
docker run -p 8080:8080 supermarket-system
```

### Quick Start
1. Create a new cart instance:
```java
Product[] initialProducts = new Product[]{
    new Electronics("Laptop", 999.99),
    new FoodProduct("Apple", 0.99)
};
Cart cart = new Cart(initialProducts);
```

2. Add products to cart:
```java
Product mouse = new Electronics("Mouse", 29.99);
cart.addProduct(mouse);
```

3. Apply discounts:
```java
cart.applyDiscountToProduct("Laptop", 0.1); // 10% discount
```

### More Detailed Examples

#### Managing Different Product Types
```java
// Create electronics with automatic 10% discount
Electronics laptop = new Electronics("Laptop", 1000.00);
laptop.applyDiscount(0.1);
System.out.println(laptop.getPrice()); // Output: 900.0

// Create food product with 5% discount
FoodProduct apple = new FoodProduct("Apple", 1.00);
apple.applyDiscount(0.05);
System.out.println(apple.getPrice()); // Output: 0.95
```

#### Cart Operations
```java
// Update product quantity
cart.updateProductQuantity("Laptop", 2);

// Remove product
cart.removeProduct("Apple");

// Clear cart
cart.clear();
```

### Troubleshooting

#### Common Issues

1. JDK Version Mismatch
```
Error: Unable to initialize main class org.example.ManagementSystem
Caused by: java.lang.UnsupportedClassVersionError
```
Solution: Ensure you're using JDK 17 or later
```bash
java -version
```

2. Port Already in Use
```
Web server failed to start. Port 8080 was already in use.
```
Solution: Modify the port in application.yaml or kill the process using the port
```bash
# Find process using port 8080
lsof -i :8080
# Kill the process
kill -9 <PID>
```

## Data Flow
The system processes shopping cart operations through a thread-safe implementation that manages product inventory and applies discounts based on product type.

```ascii
[Customer] -> [ManagementSystem]
                     |
                     v
[Cart Operations] -> [Cart]
         |             |
         v             v
    [Products] <- [Discount Rules]
```

Key component interactions:
1. ManagementSystem initializes the shopping cart and customer interface
2. Cart maintains a thread-safe HashMap of products and quantities
3. Products implement the Discountable interface for type-specific discounts
4. Electronics products automatically apply 10% discount
5. Food products automatically apply 5% discount
6. Cart operations are handled through a separate thread for user interaction
7. Product updates are synchronized to prevent concurrent modification issues