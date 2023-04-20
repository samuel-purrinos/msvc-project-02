package com.uichesoh.stock;

import com.uichesoh.stock.model.Stock;
import com.uichesoh.stock.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(StockRepository stockRepository){
		return args -> {
			Stock stock1 = Stock.builder()
					.skuCode("iphone_12")
					.quantity(100)
					.build();
			Stock stock2 = Stock.builder()
					.skuCode("laptop_HP")
					.quantity(7)
					.build();
			Stock stock3 = Stock.builder()
					.skuCode("iphone_12_blue")
					.quantity(0)
					.build();
			stockRepository.save(stock1);
			stockRepository.save(stock2);
			stockRepository.save(stock3);

		};
	}

}
