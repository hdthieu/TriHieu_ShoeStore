package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Order;
import com.shoestore.Server.entities.OrderDetail;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.ProductDetail;
import com.shoestore.Server.service.OrderDetailService;
import com.shoestore.Server.service.OrderService;
import com.shoestore.Server.service.ProductDetailService;
import com.shoestore.Server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/OrderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/top-selling")
    public ResponseEntity<List<Product>> getTopSellingProducts(
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "5") int limit) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();
        switch (type) {
            case "day":
                startDate = endDate.minusDays(1);
                break;
            case "week":
                startDate = endDate.minusWeeks(1);
                break;
            case "month":
                startDate = endDate.minusMonths(1);
                break;
            case "year":
                startDate = endDate.minusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        List<Product> products = orderDetailService.getTopSellingProducts(startDate, endDate, limit);
        return ResponseEntity.ok(products);
    }

    // API lấy thông tin chi tiết đơn hàng theo orderID
    @GetMapping("/layTT/{orderID}")
    public ResponseEntity<Map<String, Object>> getOrderDetailByOrderID(@PathVariable Long orderID) {
        Map<String, Object> orderDetail = orderDetailService.fetchOrderDetailByOrderID(orderID);
        return ResponseEntity.ok(orderDetail);
    }

//    @PostMapping("/addProductToOrder")
//    public ResponseEntity<?> addProductToOrder(@RequestBody Map<String, Object> requestData) {
//        try {
//            int orderID = Integer.parseInt(requestData.get("orderID").toString());
//            int productID = Integer.parseInt(requestData.get("productID").toString());
//            int quantity = Integer.parseInt(requestData.get("quantity").toString());
//            double price = Double.parseDouble(requestData.get("price").toString());
//
//            // Lấy thông tin sản phẩm
//            List<Product> products = productService.getById(productID);
//            if (products == null || products.isEmpty()) {
//                Map<String, Object> response = new HashMap<>();
//                response.put("message", "Sản phẩm không tồn tại.");
//                response.put("success", false);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//
//            Product product = products.get(0);
//
//            // Lấy danh sách ProductDetail liên quan đến sản phẩm
//            List<ProductDetail> productDetails = product.getProductDetails();
//            if (productDetails == null || productDetails.isEmpty()) {
//                Map<String, Object> response = new HashMap<>();
//                response.put("message", "Không tìm thấy thông tin chi tiết sản phẩm.");
//                response.put("success", false);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//
//            // Kiểm tra stockQuantity
//            ProductDetail targetDetail = productDetails.get(0);
//            if (targetDetail.getStockQuantity() < quantity) {
//                Map<String, Object> response = new HashMap<>();
//                response.put("message", "Số lượng sản phẩm trong kho không đủ.");
//                response.put("success", false);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//
//            // Trừ stockQuantity
//            targetDetail.setStockQuantity(targetDetail.getStockQuantity() - quantity);
//            productDetailService.save(targetDetail); // Cập nhật ProductDetail vào DB.
//
//            // Tìm kiếm Order từ cơ sở dữ liệu
//            Order order = orderService.findById(orderID); // Sử dụng orderService để tìm Order theo ID
//            if (order == null) {
//                Map<String, Object> response = new HashMap<>();
//                response.put("message", "Đơn hàng không tồn tại.");
//                response.put("success", false);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//
//            // Tạo OrderDetail mới
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setProduct(product);
//            orderDetail.setQuantity(quantity);
//            orderDetail.setPrice(price);
//            orderDetail.setOrder(order); // Sử dụng đối tượng Order đã tìm thấy
//
//            // Lưu OrderDetail
//            orderDetailService.save(orderDetail);
//
//            // Trả về phản hồi JSON
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Sản phẩm đã được thêm vào đơn hàng.");
//            response.put("success", true);
//            return ResponseEntity.ok(response);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Lỗi khi thêm sản phẩm vào đơn hàng.");
//            response.put("success", false);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }



//    @GetMapping("/addProductToOrder/{orderID}")
//    public ResponseEntity<List<Product>> getAvailableProducts(@PathVariable int orderID) {
//        // Lấy danh sách sản phẩm chưa có trong OrderDetail
//        List<Product> availableProducts = productService.getProductsNotInOrderDetail(orderID);
//
//        // Trả về danh sách sản phẩm dưới dạng JSON
//        return ResponseEntity.ok(availableProducts);
//    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestBody Map<String, Object> requestData) {
        try {
            // Lấy giá trị từ requestData
            int productID = Integer.parseInt(requestData.get("productID").toString());
            int quantity = Integer.parseInt(requestData.get("quantity").toString());
            int orderID = Integer.parseInt(requestData.get("orderID").toString());  // Lấy orderID từ request

            // Tìm OrderDetail theo productID và orderID
            List<OrderDetail> orderDetails = orderDetailService.findByProductIDAndOrderID(productID, orderID);

            if (orderDetails.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("success", false, "message", "Sản phẩm không tồn tại trong OrderDetail"));
            }

            // Cập nhật số lượng cho từng OrderDetail
            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.setQuantity(quantity);
                orderDetailService.save(orderDetail);  // Lưu lại OrderDetail sau khi cập nhật
            }

            return ResponseEntity.ok(Map.of("success", true, "message", "Cập nhật số lượng thành công"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Có lỗi xảy ra khi cập nhật số lượng"));
        }
    }

//    @DeleteMapping("/deleteProduct/{productID}")
//    public ResponseEntity<?> deleteProduct(@PathVariable int productID, @RequestParam int orderID) {
//        try {
//            // Tìm OrderDetail theo productID và orderID
//            Optional<OrderDetail> orderDetail = orderDetailService.findByProductIDAndOrderIDDelete(productID, orderID);
//
//            if (!orderDetail.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Map.of("success", false, "message", "Sản phẩm không tồn tại trong OrderDetail"));
//            }
//
//            // Xóa sản phẩm
//            orderDetailService.deleteByProductIDAndOrderID(productID, orderID);
//
//            return ResponseEntity.ok(Map.of("success", true, "message", "Xóa sản phẩm thành công"));
//        } catch (Exception e) {
//            e.printStackTrace();  // Debug để kiểm tra lỗi chi tiết
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("success", false, "message", "Có lỗi xảy ra khi xóa sản phẩm"));
//        }
//    }
}
