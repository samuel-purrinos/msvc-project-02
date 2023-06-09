package com.uichesoh.stock.repository;

import com.uichesoh.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findBySkuCodeIn(List<String> skuCode);
}
