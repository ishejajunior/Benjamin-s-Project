package com.benjamin.ecommerce.service;

import com.benjamin.ecommerce.model.Product;
import com.benjamin.ecommerce.model.ShoppingCart;

public interface CartService {
    ShoppingCart getCart();

    void addToCart(Long productId, int quantity);

    void removeFromCart(Long productId);

    void updateCartItemQuantity(Long productId, int quantity);

    void clearCart();
}