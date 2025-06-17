package com.pluralsight.NorthwindTradersSpringBoot.ui;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;

import java.util.List;

public interface Displayable {
  void add(Product product);
  List<Product> getAll();
}
