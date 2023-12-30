package com.example.orderstatusservice.service;

import com.example.orderstatusservice.model.KafkaMessage;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageService {

    private final List<KafkaMessage> messages = new ArrayList<>();

    public void add(KafkaMessage message) {
        messages.add(message);
    }
}
