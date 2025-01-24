package com.expenses.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.entity.Product;
import com.expenses.service.Impl.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

 

    // Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getByCategory(category);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/cache/category/{category}")
    public ResponseEntity<String> clearCacheByCategory(@PathVariable String category) {
        productService.clearCacheByCategory(category);
        return ResponseEntity.ok("Cache cleared for category: " + category);
    }

    // Clear all caches
    @DeleteMapping("/cache")
    public ResponseEntity<String> clearAllCaches() {
        productService.clearAllCaches();
        return ResponseEntity.ok("All caches cleared");
    }
  

    
}
