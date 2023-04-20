package com.uichesoh.stock.controller;

import com.uichesoh.stock.dto.StockResponse;
import com.uichesoh.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<StockResponse> isInStock(@RequestParam List<String> skuCode){
        return stockService.isInStock(skuCode);
    }
}
