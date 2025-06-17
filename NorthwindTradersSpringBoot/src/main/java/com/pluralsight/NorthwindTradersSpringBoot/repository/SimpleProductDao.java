package com.pluralsight.NorthwindTradersSpringBoot.repository;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import com.pluralsight.NorthwindTradersSpringBoot.ui.Displayable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements Displayable {

    private final List<Product> products;

    public SimpleProductDao(List<Product> products) {
        this.products = products;
    }

//    Add product logic
    @Override
    public void add(Product product) {
        if (product == null){
            System.out.println("System Error");
        }
        products.add(product);
    }

//    get all products
    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

}
