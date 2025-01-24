package com.expenses.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.expenses.entity.Product;
import com.expenses.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "products::";

    // Retrieve products by category from Redis or database
    public List<Product> getByCategory(String category) {
        String redisKey = REDIS_KEY_PREFIX + category;

        // Check if the data exists in Redis
        List<Product> products = (List<Product>) redisTemplate.opsForValue().get(redisKey);
        if (products != null) {
            System.out.println("Fetching data from Redis for category: " + category);
            return products;
        }

        // Fetch from database and save to Redis
        System.out.println("Fetching data from database for category: " + category);
        products = productRepository.findByCategory(category);
        redisTemplate.opsForValue().set(redisKey, products);
        return products;
    }

    // Save product and update cache
    public Product saveProduct(Product product) {
        // Save to database
        Product savedProduct = productRepository.save(product);

        // Update Redis cache for the category
        String redisKey = REDIS_KEY_PREFIX + product.getCategory();
        List<Product> products = productRepository.findByCategory(product.getCategory());
        redisTemplate.opsForValue().set(redisKey, products);

        System.out.println("Product saved and cache updated for category: " + product.getCategory());
        return savedProduct;
    }

    // Clear Redis cache for a specific category
    public void clearCacheByCategory(String category) {
        String redisKey = REDIS_KEY_PREFIX + category;
        redisTemplate.delete(redisKey);
        System.out.println("Cache cleared for category: " + category);
    }

    // Clear all product caches
    public void clearAllCaches() {
        redisTemplate.delete(redisTemplate.keys(REDIS_KEY_PREFIX + "*"));
        System.out.println("All product caches cleared");
    }
}
