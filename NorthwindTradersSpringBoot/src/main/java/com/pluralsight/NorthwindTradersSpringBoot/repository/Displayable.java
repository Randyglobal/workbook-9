package com.pluralsight.NorthwindTradersSpringBoot.repository;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;

import java.util.List;

public interface Displayable {
  void add(Product product);
  List<Product> getAll();
  void delete(int id);
  void update(int id, Product updateTerm);
  List<Product> search(String searchTerm);
}
