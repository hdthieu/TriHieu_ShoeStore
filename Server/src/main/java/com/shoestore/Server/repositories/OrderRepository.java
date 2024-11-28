package com.shoestore.Server.repositories;

import com.shoestore.Server.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT SUM(od.quantity), " +
            "SUM(od.quantity * od.price - " +
            "CASE " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Percentage' THEN (o.voucher.discountValue / 100 * (od.quantity * od.price)) " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Flat' THEN o.voucher.discountValue " +
            "ELSE 0 END) " +
            "FROM OrderDetail od JOIN od.order o " +
            "WHERE YEAR(o.orderDate) = :year")
    List<Object[]> findTotalRevenueAndQuantityByYear(@Param("year") int year);

    @Query("SELECT COUNT(DISTINCT o.orderID), " +  // Tổng số đơn hàng
            "SUM(od.quantity), " +  // Tổng số lượng sản phẩm
            "SUM(od.quantity * od.price), " +  // Tổng doanh thu trước giảm giá
            "SUM(od.quantity * od.price - " +  // Doanh thu sau khi giảm giá
            "CASE " +
            "   WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Percentage' THEN (o.voucher.discountValue / 100 * (od.quantity * od.price)) " +
            "   WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Flat' THEN o.voucher.discountValue  " +
            "   ELSE 0 " +
            "END) " +  // Tính giảm giá từ voucher (Flat hoặc Percentage)
            "FROM Order o JOIN o.orderDetails od " +  // Join từ Order sang OrderDetail
            "WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Object[]> findRevenueAndDiscountedRevenueBetweenDates(@Param("startDate") LocalDate startDate,
                                                               @Param("endDate") LocalDate endDate);







    @Query("SELECT o.user, COUNT(o), SUM(od.quantity * od.price - " +
            "CASE " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Percentage' THEN (od.quantity * od.price * o.voucher.discountValue / 100) " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Flat' THEN o.voucher.discountValue " +
            "ELSE 0 END) " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "WHERE o.user.role.name = 'Customer' " +
            "GROUP BY o.user " +
            "HAVING COUNT(o) >= :minOrders " +
            "ORDER BY SUM(od.quantity * od.price - " +
            "CASE " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Percentage' THEN (od.quantity * od.price * o.voucher.discountValue / 100) " +
            "WHEN o.voucher IS NOT NULL AND o.voucher.discountType = 'Flat' THEN o.voucher.discountValue " +
            "ELSE 0 END) DESC")
    List<Object[]> findLoyalCustomers(@Param("minOrders") int minOrders);

    long countByStatus(String status);



}
