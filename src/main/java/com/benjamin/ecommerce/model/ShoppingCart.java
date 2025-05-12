package com.benjamin.ecommerce.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public void addItem(Product product, int quantity) {
        // Check if product already exists in cart
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.updateSubtotal();
                calculateTotal();
                return;
            }
        }

        // Add new item if it doesn't exist
        CartItem newItem = new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartItems.add(newItem);
        calculateTotal();
    }

    public void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
        calculateTotal();
    }

    public void updateItemQuantity(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                item.updateSubtotal();
                calculateTotal();
                return;
            }
        }
    }

    public void clear() {
        cartItems.clear();
        totalAmount = BigDecimal.ZERO;
    }

    private void calculateTotal() {
        totalAmount = BigDecimal.ZERO;
        for (CartItem item : cartItems) {
            totalAmount = totalAmount.add(item.getSubtotal());
        }
    }
}