package com.ecommerce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.AddToCartRequest;
import com.ecommerce.app.entity.CartItem;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.repository.CartItemRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItem addToCart(AddToCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        CartItem item = cartItemRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElse(null);

        int newQuantity = request.getQuantity();
        if (item != null) {
            newQuantity += item.getQuantity();
        }

        if (newQuantity > product.getStock()) {
            throw new IllegalArgumentException("Not enough stock. Available: " + product.getStock());
        }

        if (item == null) {
            item = new CartItem();
            item.setUser(user);
            item.setProduct(product);
        }
        item.setQuantity(newQuantity);
        return cartItemRepository.save(item);
    }

    public List<CartItem> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void removeItem(Long itemId) {
        if (!cartItemRepository.existsById(itemId)) {
            throw new RuntimeException("Cart item not found with id: " + itemId);
        }
        cartItemRepository.deleteById(itemId);
    }
}