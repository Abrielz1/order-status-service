package com.example.orderstatusservice.service;

import com.example.orderstatusservice.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageService {

   // private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private final List<KafkaMessage> messages = new ArrayList<>();

    public void add(KafkaMessage message) {
        messages.add(message);
    }

//    @KafkaListener(topics = "${app.kafka.topicToWrite}",
//            groupId = "${app.kafka.kafkaMessageGroupId}",
//            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
//    public void send(@Payload KafkaMessage message,
//                     @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
//                     @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
//                     @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
//                     @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {
//
//        log.info("Received message: {}", message);
//        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timeStamp);
//
//            doSomethingWithMessage(topic, message);
//    }
//
//    public void doSomethingWithMessage(String topicName, KafkaMessage message){
//
//        kafkaTemplate.send(topicName, message);
//    }
}
