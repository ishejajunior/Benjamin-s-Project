package com.benjamin.ecommerce.config;

import com.benjamin.ecommerce.model.Product;
import com.benjamin.ecommerce.model.User;
import com.benjamin.ecommerce.service.ProductService;
import com.benjamin.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserService userService;
    private final ProductService productService;

    @Bean
    @Profile("!prod")
    public CommandLineRunner initData() {
        return args -> {
            // Create admin user if it doesn't exist
            if (!userService.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword("password");
                admin.setRoles(new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
                userService.registerNewUser(admin);
            }

            // Create regular user if it doesn't exist
            if (!userService.existsByUsername("user")) {
                User user = new User();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword("password");
                user.setRoles(Collections.singleton("ROLE_USER"));
                userService.registerNewUser(user);
            }

            // Add sample products if none exist
            if (productService.findAllProducts().isEmpty()) {
                // Electronics
                Product laptop = new Product();
                laptop.setName("Laptop Pro 15");
                laptop.setDescription("High-performance laptop with 16GB RAM, 512GB SSD, and 15-inch display");
                laptop.setPrice(new BigDecimal("999.99"));
                laptop.setStockQuantity(10);
                laptop.setCategory("electronics");
                laptop.setImageUrl("https://via.placeholder.com/300x200?text=Laptop");
                productService.saveProduct(laptop);

                Product smartphone = new Product();
                smartphone.setName("SmartPhone X");
                smartphone.setDescription(
                        "Latest smartphone with 6.5-inch OLED display, 128GB storage, and triple camera system");
                smartphone.setPrice(new BigDecimal("699.99"));
                smartphone.setStockQuantity(15);
                smartphone.setCategory("electronics");
                smartphone.setImageUrl("https://via.placeholder.com/300x200?text=Smartphone");
                productService.saveProduct(smartphone);

                // Clothing
                Product tShirt = new Product();
                tShirt.setName("Cotton T-Shirt");
                tShirt.setDescription("100% cotton t-shirt available in multiple colors");
                tShirt.setPrice(new BigDecimal("19.99"));
                tShirt.setStockQuantity(50);
                tShirt.setCategory("clothing");
                tShirt.setImageUrl("https://via.placeholder.com/300x200?text=TShirt");
                productService.saveProduct(tShirt);

                Product jeans = new Product();
                jeans.setName("Slim Fit Jeans");
                jeans.setDescription("Comfortable slim fit jeans with stretch material");
                jeans.setPrice(new BigDecimal("49.99"));
                jeans.setStockQuantity(30);
                jeans.setCategory("clothing");
                jeans.setImageUrl("https://via.placeholder.com/300x200?text=Jeans");
                productService.saveProduct(jeans);

                // Home & Garden
                Product coffeeMaker = new Product();
                coffeeMaker.setName("Coffee Maker");
                coffeeMaker.setDescription("Programmable coffee maker with 12-cup capacity");
                coffeeMaker.setPrice(new BigDecimal("79.99"));
                coffeeMaker.setStockQuantity(20);
                coffeeMaker.setCategory("home");
                coffeeMaker.setImageUrl("https://via.placeholder.com/300x200?text=CoffeeMaker");
                productService.saveProduct(coffeeMaker);

                Product plantPot = new Product();
                plantPot.setName("Ceramic Plant Pot");
                plantPot.setDescription("Decorative ceramic plant pot with drainage hole");
                plantPot.setPrice(new BigDecimal("24.99"));
                plantPot.setStockQuantity(40);
                plantPot.setCategory("home");
                plantPot.setImageUrl("https://via.placeholder.com/300x200?text=PlantPot");
                productService.saveProduct(plantPot);
            }
        };
    }
}