package com.uichesoh.stock.service;

import com.uichesoh.stock.dto.StockResponse;

import java.util.List;

public interface StockService {
    public List<StockResponse> isInStock(List<String> skuCodes);
}
