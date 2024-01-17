package com.example.orderstatusservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaMessage {

    private Long id;

    private String product;

    private Integer quantity;
}
