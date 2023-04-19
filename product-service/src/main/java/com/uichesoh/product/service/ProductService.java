package com.uichesoh.product.service;

import com.uichesoh.product.dto.ProductRequest;
import com.uichesoh.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);
    public List<ProductResponse> getAllProducts();
}
