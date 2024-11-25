package com.shoestore.Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoestore.Server.entities.*;
import com.shoestore.Server.service.BrandService;
import com.shoestore.Server.service.CategoryService;
import com.shoestore.Server.service.ProductService;
import com.shoestore.Server.service.SupplierService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Value("${user.dir}")  // Lấy thư mục gốc của dự án
    private String userDir;

    @Value("${file.upload-dir:src/main/resources/static}")  // Đọc đường dẫn thư mục lưu ảnh từ application.properties
    private String uploadDir;

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SupplierService supplierService;


    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService, SupplierService supplierService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.supplierService = supplierService;
    }

    @GetMapping // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getAllProducts(){
        List<Product> products=productService.getAllProduct();
        Map<String,Object> response= new HashMap<>();
        response.put("products",products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/add")
    public ResponseEntity<Map<String,Object>> getCategories(){
        Map<String,Object> response= new HashMap<>();
        response.put("categories",categoryService.getAllCategory());
        response.put("brands",brandService.getAllBrand());
        response.put("suppliers",supplierService.getAllSupplier());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(
            @RequestParam("image") MultipartFile[] files,  // Nhận mảng ảnh từ client
            @RequestParam("productName") String productName,  // Tên sản phẩm
            @RequestParam("description") String description,  // Mô tả sản phẩm
            @RequestParam("price") double price,  // Giá sản phẩm
            @RequestParam("status") String status,  // Trạng thái sản phẩm
            @RequestParam("brandID") int brandID,  // ID thương hiệu
            @RequestParam("categoryID") int categoryID,  // ID danh mục
            @RequestParam("supplierID") int supplierID  // ID nhà cung cấp
    ) {
        try {
            // Kiểm tra tham số đầu vào
            if (productName == null || productName.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Tên sản phẩm không được để trống.");
            }

            if (files.length == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Vui lòng tải lên ít nhất một ảnh sản phẩm.");
            }

            // Đường dẫn động cho thư mục lưu ảnh
            String uploadDirPath = userDir + File.separator + uploadDir + File.separator + "images";
            File directory = new File(uploadDirPath);
            if (!directory.exists()) {
                boolean dirsCreated = directory.mkdirs();
                if (!dirsCreated) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Không thể tạo thư mục lưu ảnh.");
                }
            }

            // Lưu các ảnh vào thư mục
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                // Lấy tên tệp ảnh và tạo đường dẫn lưu ảnh
                String fileName = file.getOriginalFilename();  // Lấy tên ảnh từ MultipartFile
                String filePath = uploadDirPath + File.separator + fileName;

                // Lưu ảnh vào thư mục
                File destFile = new File(filePath);
                file.transferTo(destFile);  // Lưu tệp vào thư mục

                // Tạo URL cho ảnh
                String imageUrl = "http://localhost:8080/images/" + fileName;
                imageUrls.add(imageUrl);  // Lưu URL ảnh vào danh sách
            }

            // Tạo đối tượng Product từ các tham số nhận được
            Product product = new Product();
            product.setProductName(productName);
            product.setDescription(description);
            product.setPrice(price);
            product.setStatus(status);

            // Tạo đối tượng Brand, Category và Supplier từ ID
            product.setBrand(new Brand(brandID));
            product.setCategory(new Category(categoryID));
            product.setSupplier(new Supplier(supplierID));

            // Cập nhật đường dẫn ảnh vào thông tin sản phẩm
            product.setImageURL(imageUrls);  // Lưu danh sách URL ảnh vào product

            // Lưu sản phẩm vào cơ sở dữ liệu
            Product savedProduct = productService.saveProduct(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã xảy ra lỗi khi xử lý yêu cầu. Vui lòng thử lại.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi không xác định: " + e.getMessage());
        }
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok("Sản phẩm đã được xóa thành công");  // Trả về thông báo thành công
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm để xóa");  // Trả về thông báo lỗi nếu không tìm thấy sản phẩm
        }
    }

    @GetMapping("/detailFor/{id}")
    public ResponseEntity<Product> getProductByIdForDetail(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product updatedProduct) {
        try {
            // Đảm bảo ID của sản phẩm được gán đúng
            updatedProduct.setProductID(id);

            // Lưu trực tiếp sản phẩm đã chỉnh sửa
            Product savedProduct = productService.saveProduct(updatedProduct);

            // Trả về sản phẩm đã cập nhật
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/{id}") // Ánh xạ HTTP GET
    public ResponseEntity<Map<String,Object>> getProductsById(@PathVariable int id){
        List<Product> products=productService.getById(id);
        Map<String,Object> response= new HashMap<>();
        response.put("product",products);
        return ResponseEntity.ok(response);
    }

}
