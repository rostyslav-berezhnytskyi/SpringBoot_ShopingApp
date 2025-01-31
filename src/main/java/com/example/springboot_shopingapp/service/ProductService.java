package com.example.springboot_shopingapp.service;

import com.example.springboot_shopingapp.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> findAll();

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product findById(Long id);

    List<String> getUppercaseTitles();
}
