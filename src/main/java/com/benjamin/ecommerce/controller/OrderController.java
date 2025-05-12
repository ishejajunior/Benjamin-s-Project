package com.benjamin.ecommerce.controller;

import com.benjamin.ecommerce.model.Order;
import com.benjamin.ecommerce.model.OrderItem;
import com.benjamin.ecommerce.model.ShoppingCart;
import com.benjamin.ecommerce.model.User;
import com.benjamin.ecommerce.service.CartService;
import com.benjamin.ecommerce.service.OrderService;
import com.benjamin.ecommerce.service.ProductService;
import com.benjamin.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public String viewOrders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            userService.findByUsername(userDetails.getUsername()).ifPresent(user -> {
                model.addAttribute("orders", orderService.findOrdersByUser(user));
            });
        }
        return "orders/list";
    }

    @GetMapping("/{id}")
    public String viewOrderDetails(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {
        // Only allow users to view their own orders or admins to view any order
        if (userDetails != null) {
            orderService.findOrderById(id).ifPresent(order -> {
                User user = userService.findByUsername(userDetails.getUsername()).orElse(null);

                // Check if the order belongs to the user or user is admin
                if (user != null && (order.getUser().getId().equals(user.getId()) ||
                        user.getRoles().contains("ROLE_ADMIN"))) {
                    model.addAttribute("order", order);
                    return;
                }
            });
        }
        return "orders/details";
    }

    @PostMapping("/place")
    public String placeOrder(@AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        // Validate user and cart
        if (userDetails == null) {
            return "redirect:/login";
        }

        ShoppingCart cart = cartService.getCart();
        if (cart.getCartItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty");
            return "redirect:/cart";
        }

        // Get user from database
        User user = userService.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        // Create and save order
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setTotalAmount(cart.getTotalAmount());
        newOrder.setStatus(Order.OrderStatus.PENDING);

        // Create order items from cart items
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());

            // Get product from the database by ID
            productService.findProductById(cartItem.getProductId()).ifPresent(orderItem::setProduct);

            newOrder.getOrderItems().add(orderItem);
        });

        // Save order
        orderService.placeOrder(newOrder);

        // Clear the cart
        cartService.clearCart();

        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
        return "redirect:/orders";
    }

    // Admin order management

    @GetMapping("/admin")
    public String adminViewAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "admin/orders/list";
    }

    @GetMapping("/admin/{id}")
    public String adminViewOrderDetails(@PathVariable Long id, Model model) {
        orderService.findOrderById(id).ifPresent(order -> model.addAttribute("order", order));
        return "admin/orders/details";
    }

    @PostMapping("/admin/{id}/status")
    public String updateOrderStatus(@PathVariable Long id,
            @RequestParam Order.OrderStatus status,
            RedirectAttributes redirectAttributes) {
        try {
            orderService.updateOrderStatus(id, status);
            redirectAttributes.addFlashAttribute("message", "Order status updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating order status: " + e.getMessage());
        }
        return "redirect:/orders/admin/" + id;
    }
}