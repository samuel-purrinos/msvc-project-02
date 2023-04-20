package com.uichesoh.stock.controller;

import com.uichesoh.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("skuCode") String skuCode){
        return stockService.isInStock(skuCode);
    }
}
