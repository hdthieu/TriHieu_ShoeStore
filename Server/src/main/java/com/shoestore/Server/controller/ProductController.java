package com.shoestore.Server.controller;

import com.shoestore.Server.entities.Product;
import com.shoestore.Server.entities.Voucher;
import com.shoestore.Server.service.BrandService;
import com.shoestore.Server.service.CategoryService;
import com.shoestore.Server.service.ProductService;
import com.shoestore.Server.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

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
    public ResponseEntity<Map<String,Object>> getProducts(){
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
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            // Lưu sản phẩm vào cơ sở dữ liệu
            Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
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


}
