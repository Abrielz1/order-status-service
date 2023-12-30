package com.example.orderstatusservice.model;

import lombok.Data;

@Data
public class KafkaMessage {

    private Long id;

    private String product;

    private Integer quantity;
}
