package com.uichesoh.order.service;

import com.uichesoh.order.dto.OrderRequest;
import com.uichesoh.order.dto.StockResponse;

import java.util.List;

public interface OrderService {
    public String placeOrder(OrderRequest orderRequest);
}
