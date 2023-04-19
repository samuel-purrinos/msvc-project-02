package com.uichesoh.product.service;

import com.uichesoh.product.dto.ProductRequest;
import com.uichesoh.product.dto.ProductResponse;
import com.uichesoh.product.model.Product;
import com.uichesoh.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} successfully saved",product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        log.info("Getting all products");
        return allProducts.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
