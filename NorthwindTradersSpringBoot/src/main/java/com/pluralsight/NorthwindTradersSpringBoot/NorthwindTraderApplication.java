package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import com.pluralsight.NorthwindTradersSpringBoot.repository.Displayable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class NorthwindTraderApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("JdbcProductDao")
    private Displayable productDao;

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Product Admin Menu ===");
                System.out.println("1. List Products");
                System.out.println("2. Add Product");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Search Products");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        List<Product> products = productDao.getAll();
                        System.out.println("\nProducts:");
                        for (Product product : products) {
                            System.out.println(product);
                        }
                        break;

                    case "2":
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        Product newProduct = new Product();
                        newProduct.setName(name);
                        newProduct.setPrice(price);

                        try {
                            productDao.add(newProduct);
                            System.out.println("Product added successfully.");
                        } catch (Exception e) {
                            System.out.println("Failed to add product");
                        }
                        break;

                    case "3":
                        System.out.println("Enter the id of the product you want to update");
                        int productId = scanner.nextInt();
                        scanner.nextLine(); // Consume leftover newline


                        System.out.print("Enter product name: ");
                        String updatedName = scanner.nextLine();

                        double updatedPrice;
                        while (true) {
                            try {
                                System.out.print("Enter product price: ");
                                updatedPrice = scanner.nextDouble();
                                scanner.nextLine();
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                            }
                        }
                        Product updatedProduct = new Product(productId, updatedName, updatedPrice);

                        try {
                            productDao.update(productId, updatedProduct);
                            System.out.println("Product updated successfully.");
                        } catch (Exception e) {
                            System.out.println("Product failed to update.");
                        }
                        break;

                    case "4":
                        System.out.println("Enter the id of the product you want to delete");
                        int productToDeleteId = scanner.nextInt();
                        scanner.nextLine();
//
                        try {
                            productDao.delete(productToDeleteId);
                            System.out.println("Product deleted successfully.");
                        } catch (Exception e) {
                            System.out.println("Deletion of product failed");
                        }
                        break;

                    case "5":
                        System.out.println("Enter the name of the product you want to search for: ");
                        String searchName = scanner.nextLine();
                        productDao.search(searchName).forEach(System.out::println);
                        break;

                    case "0":
                        System.out.println("Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
}
