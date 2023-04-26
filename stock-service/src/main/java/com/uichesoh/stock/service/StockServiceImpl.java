package com.uichesoh.stock.service;

import com.uichesoh.stock.dto.StockResponse;
import com.uichesoh.stock.repository.StockRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<StockResponse> isInStock(List<String> skuCodes) {
        log.info("Checking stock for sku code ",skuCodes);
        return stockRepository.findBySkuCodeIn(skuCodes).stream()
                .map(stock ->
                        StockResponse.builder()
                                .skuCode(stock.getSkuCode())
                                .hasStock(stock.getQuantity()>0)
                                .build()
                ).collect(Collectors.toList());
    }
}
