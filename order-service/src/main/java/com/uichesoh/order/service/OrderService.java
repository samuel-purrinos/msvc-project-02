package com.uichesoh.order.service;

import com.uichesoh.order.dto.OrderRequest;

public interface OrderService {
    public void placeOrder(OrderRequest orderRequest);
}
