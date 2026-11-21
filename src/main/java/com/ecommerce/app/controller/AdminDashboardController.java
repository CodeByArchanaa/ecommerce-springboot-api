package com.ecommerce.app.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.Order;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.UserRepository;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private static final Set<String> REVENUE_STATUS =
            Set.of("PAID", "SHIPPED", "DELIVERED");

    public AdminDashboardController(UserRepository userRepository,
                                    ProductRepository productRepository,
                                    OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public Map<String, Object> getStats() {
        double revenue = orderRepository.findAll().stream()
                .filter(o -> REVENUE_STATUS.contains(o.getStatus()))
                .map(Order::getTotalAmount)
                .filter(a -> a != null)
                .mapToDouble(Double::doubleValue)
                .sum();

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalProducts", productRepository.count());
        stats.put("totalOrders", orderRepository.count());
        stats.put("totalRevenue", revenue);
        return stats;
    }
}