import java.util.*;

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

public class InventoryBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.println("=== Inventory Billing System ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Show Inventory");
            System.out.println("3. Bill a Product");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                products.add(new Product(name, quantity, price));
                System.out.println("Product added successfully!");
            } else if (choice == 2) {
                System.out.println("\nInventory:");
                for (Product p : products) {
                    System.out.println(p.name + " | Qty: " + p.quantity + " | Price: $" + p.price);
                }
            } else if (choice == 3) {
                System.out.print("Enter product name to bill: ");
                String billName = sc.nextLine();
                boolean found = false;
                for (Product p : products) {
                    if (p.name.equalsIgnoreCase(billName)) {
                        System.out.print("Enter quantity to buy: ");
                        int qty = sc.nextInt();
                        if (qty <= p.quantity) {
                            double total = qty * p.price;
                            p.quantity -= qty;
                            System.out.println("Total bill: $" + total);
                        } else {
                            System.out.println("Not enough stock!");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Product not found!");
                sc.nextLine(); // consume newline
            } else if (choice == 4) {
                System.out.println("Exiting... Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
