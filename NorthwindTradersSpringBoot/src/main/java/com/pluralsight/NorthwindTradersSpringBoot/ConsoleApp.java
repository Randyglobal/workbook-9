package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import com.pluralsight.NorthwindTradersSpringBoot.repository.SimpleProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//making a bean
@Component
public class ConsoleApp implements CommandLineRunner {

//    injection
    @Autowired
    List<Product> productList = new ArrayList<>();
    SimpleProductDao productDao = new SimpleProductDao(productList);

    @Override
    public void run(String... args) throws Exception {
        Product laptop = new Product(1,"Laptop", "Electronics", 1200.00);
        productDao.add(laptop);
        Product mouse = new Product(2,"Mouse", "Electronics", 25.50);
        productDao.add(mouse);

        Product keyboard = new Product(3,"Mechanical Keyboard", "Peripherals", 150.00);
        productDao.add(keyboard);

        System.out.println("\n--- Products in SimpleProductDao ---");
        for (Product p : productDao.getAll()) {
            System.out.println(p.getName() + " - " + p.getCategory() + " - $" + String.format("%.2f", p.getPrice()));
        }
        System.out.println("------------------------------------");

        System.out.println("\n--- Searching for Products by ID ---");
        Product foundProduct1 = productDao.getSearchTerm(1);
        if (foundProduct1 != null) {
            System.out.println("Found Product with ID 1: " + foundProduct1.getName());
        } else {
            System.out.println("Product with ID 1 not found.");
        }
        Product deleteProduct1 = productDao.deleteProduct(2);
        if (deleteProduct1 != null) {
            System.out.println("Deleted Product with ID 2: " + deleteProduct1.getName());
            System.out.println("\n--- Products in SimpleProductDao ---");
            for (Product p : productDao.getAll()) {
                System.out.println(p.getName() + " - " + p.getCategory() + " - $" + String.format("%.2f", p.getPrice()));
            }
            System.out.println("------------------------------------");
        } else {
            System.out.println("Product with ID 1 not found.");
        }
    }
    public static void searchTerm(){


    }
}
