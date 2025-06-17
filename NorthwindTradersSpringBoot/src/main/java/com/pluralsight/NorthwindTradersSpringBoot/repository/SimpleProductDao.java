package com.pluralsight.NorthwindTradersSpringBoot.repository;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import com.pluralsight.NorthwindTradersSpringBoot.ui.Displayable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
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

//    Search
    public Product getSearchTerm(int id){
        for (Product product: products){
            if (product.getProductId() == id){
                return product;
            }
        }
        return null;
    }

//    delete
public Product deleteProduct(int id){
    Iterator<Product> iterator = products.iterator();

    while (iterator.hasNext()){
        Product product = iterator.next();
        if (product.getProductId() == id){
            iterator.remove();
            return product;
        }
    }
    return null;
}

}
