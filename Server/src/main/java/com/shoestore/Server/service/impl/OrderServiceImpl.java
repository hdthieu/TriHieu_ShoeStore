package com.shoestore.Server.service.impl;

import com.shoestore.Server.entities.Order;
import com.shoestore.Server.repositories.OrderRepository;
import com.shoestore.Server.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void updateOrderStatus(int orderId, String status) {
        // Tìm đơn hàng theo ID
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy đơn hàng với ID: " + orderId);
        }
        Order order = optionalOrder.get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public Order findById(int orderID) {
        return orderRepository.findById(orderID).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        String jpql = """
            SELECT o 
            FROM Order o
            JOIN FETCH o.user u
            JOIN FETCH o.orderDetails od
            JOIN FETCH od.productDetail pd
            JOIN FETCH pd.product p
            ORDER BY o.orderDate ASC
        """;

        return entityManager.createQuery(jpql, Order.class).getResultList();
    }


    public Map<String, Object> getRevenueStatistics(LocalDate startDate, LocalDate endDate) {
        // Thực hiện truy vấn để lấy tổng số đơn hàng, tổng doanh thu và doanh thu đã giảm
        List<Object[]> result = orderRepository.findRevenueAndDiscountedRevenueBetweenDates(startDate, endDate);

        Map<String, Object> data = new HashMap<>();

        if (result != null && !result.isEmpty()) {
            Object[] row = result.get(0);

            data.put("totalOrders", row[0] != null ? row[0] : 0);
            data.put("totalQuantity", row[1] != null ? row[1] : 0);
            data.put("totalRevenueNotComplete", row[2] != null ? row[2] : 0);
            data.put("totalRevenue", row[3] != null ? row[3] : 0);
        } else {
            // Nếu không có dữ liệu, gán tất cả giá trị là 0
            data.put("totalOrders", 0);
            data.put("totalQuantity", 0);
            data.put("totalRevenue", 0);
            data.put("totalDiscountedRevenue", 0);
        }

        return data;
    }


    // lấy doanh thu 1 năm và số sản phẩm
    @Override
    public Map<String, Object> getRevenueAndQuantityForCurrentYear() {
        int currentYear = LocalDate.now().getYear();
        List<Object[]> result = orderRepository.findTotalRevenueAndQuantityByYear(currentYear);
        Map<String, Object> data = new HashMap<>();
        if (result != null && !result.isEmpty()) {
            Object[] row = result.get(0);
            data.put("totalQuantity", row[0] != null ? row[0] : 0);
            data.put("totalRevenue", row[1] != null ? row[1] : 0);
        } else {
            data.put("totalQuantity", 0);
            data.put("totalRevenue", 0);
        }
        return data;
    }

    @Override
    public List<Object[]> getTop10LoyalCustomers(int minOrders) {
        List<Object[]> loyalCustomers = orderRepository.findLoyalCustomers(minOrders);
        return loyalCustomers.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getOrderStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("Processing", orderRepository.countByStatus("Processing"));
        statistics.put("Shipped", orderRepository.countByStatus("Shipped"));
        statistics.put("Delivered", orderRepository.countByStatus("Delivered"));
        statistics.put("Return", orderRepository.countByStatus("Return"));
        return statistics;
    }



}
