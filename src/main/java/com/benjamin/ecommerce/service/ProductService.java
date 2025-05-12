package com.benjamin.ecommerce.service;

import com.benjamin.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByCategory(String category);

    List<Product> searchProducts(String keyword);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}