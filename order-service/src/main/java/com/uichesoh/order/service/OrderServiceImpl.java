package com.uichesoh.order.service;

import com.uichesoh.order.dto.OrderLineItemsDto;
import com.uichesoh.order.dto.OrderRequest;
import com.uichesoh.order.dto.StockResponse;
import com.uichesoh.order.model.Order;
import com.uichesoh.order.model.OrderLineItems;
import com.uichesoh.order.repository.OrderRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Override
    @Transactional
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        log.info("Order : {}. skuCodes : {}",order.getOrderNumber(),skuCodes);

        StockResponse[] stockResponseArray = webClientBuilder.build().get()
                .uri("http://stock-service/api/v1/stock/",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(StockResponse[].class)
                .block();

        boolean allProductsHaveStock = Arrays.stream(stockResponseArray).allMatch(StockResponse::isHasStock);
        if(allProductsHaveStock){
            orderRepository.save(order);
            log.info("Order {} placed succesfully",order.getOrderNumber());
            return "Order placed succesfully";
        }else{
            log.error("Order {} not placed due running out of product stock",order.getOrderNumber());
            throw new IllegalArgumentException("Product hasn´t stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
