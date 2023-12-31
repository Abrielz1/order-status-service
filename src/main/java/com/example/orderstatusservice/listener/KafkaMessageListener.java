package com.example.orderstatusservice.listener;

import com.example.orderstatusservice.model.KafkaMessage;
import com.example.orderstatusservice.service.KafkaMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final KafkaMessageService kafkaMessageService;

    private KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic",
                   groupId = "${app.kafka.kafkaMessageGroupId",
                   containerFactory =  "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timeStamp);

    //    kafkaMessageService.add(message);

        kafkaMessageService.doSomethingWithMessage(message);
    }

    public void doSomethingWithMessage(String topicName, KafkaMessage message){

        kafkaTemplate.send(topicName, message);
    }
}
