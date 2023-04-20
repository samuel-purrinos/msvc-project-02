package com.uichesoh.stock.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
