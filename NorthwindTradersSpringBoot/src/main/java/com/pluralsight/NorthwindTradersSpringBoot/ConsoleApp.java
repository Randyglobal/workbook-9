package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import com.pluralsight.NorthwindTradersSpringBoot.repository.SimpleProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//making a bean
@Component
public class ConsoleApp implements CommandLineRunner {

//    injection
    @Autowired
    List<Product> productList = new ArrayList<>();
    SimpleProductDao productDao = new SimpleProductDao(productList);

    @Override
    public void run(String... args) throws Exception {
        Product laptop = new Product("Laptop", "Electronics", 1200.00);
        productDao.add(laptop);
        Product mouse = new Product("Mouse", "Electronics", 25.50);
        productDao.add(mouse);

        Product keyboard = new Product("Mechanical Keyboard", "Peripherals", 150.00);
        productDao.add(keyboard);

        System.out.println("\n--- Products in SimpleProductDao ---");
        for (Product p : productDao.getAll()) {
            System.out.println(p.getName() + " - " + p.getCategory() + " - $" + String.format("%.2f", p.getPrice()));
        }
        System.out.println("------------------------------------");
    }
}
