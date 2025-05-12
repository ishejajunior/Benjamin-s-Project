package com.benjamin.ecommerce.service.impl;

import com.benjamin.ecommerce.model.Product;
import com.benjamin.ecommerce.model.ShoppingCart;
import com.benjamin.ecommerce.service.CartService;
import com.benjamin.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ShoppingCart cart = new ShoppingCart();
    private final ProductService productService;

    @Override
    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public void addToCart(Long productId, int quantity) {
        productService.findProductById(productId).ifPresent(product -> {
            // Check if there's enough stock
            if (product.getStockQuantity() >= quantity) {
                cart.addItem(product, quantity);
            } else {
                throw new IllegalArgumentException("Not enough stock available");
            }
        });
    }

    @Override
    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    @Override
    public void updateCartItemQuantity(Long productId, int quantity) {
        productService.findProductById(productId).ifPresent(product -> {
            if (product.getStockQuantity() >= quantity) {
                cart.updateItemQuantity(productId, quantity);
            } else {
                throw new IllegalArgumentException("Not enough stock available");
            }
        });
    }

    @Override
    public void clearCart() {
        cart.clear();
    }
}