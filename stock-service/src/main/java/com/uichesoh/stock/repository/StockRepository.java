package com.uichesoh.stock.repository;

import com.uichesoh.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findBySkuCode(String skuCode);
}
