package com.uichesoh.stock.service;

import com.uichesoh.stock.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        log.info("Checking stock for sku code ",skuCode);
        return stockRepository.findBySkuCode(skuCode).isPresent();
    }
}
