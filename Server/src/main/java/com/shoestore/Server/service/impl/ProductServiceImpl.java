package com.shoestore.Server.service.impl;

import com.shoestore.Server.dto.ProductDTO;
import com.shoestore.Server.entities.Product;
import com.shoestore.Server.repositories.OrderDetailRepository;
import com.shoestore.Server.repositories.ProductRepository;
import com.shoestore.Server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // này của hiếu
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);

    }
    public List<Product> getById(int id) {
        return productRepository.findByProductID(id);

    }

    // nay cua hieu
    public List<Product> getProductsNotInOrderDetail(int orderID) {
        List<Integer> productIDsInOrderDetail = orderDetailRepository.findProductIDsByOrderID(orderID);
        if (productIDsInOrderDetail.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.findByProductIDNotIn(productIDsInOrderDetail);
        }
    }

    @Override
    public List<ProductDTO> getTop10BestSellers() {
        return productRepository.findTop10BestSellers(PageRequest.of(0, 10));
    }

    @Override
    public List<ProductDTO> getTop10NewArrivals() {
        return productRepository.findTop10NewArrivals(PageRequest.of(0, 10));
    }

    @Override
    public List<ProductDTO> getTop10Trending() {
        return productRepository.findTop10Trending(PageRequest.of(0, 10));
    }


}
