package com.example.orderstatusservice.listener;

import com.example.orderstatusservice.model.KafkaMessage;
import com.example.orderstatusservice.model.KafkaMessageDTO;
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

    //todo: выкинуть логику в сервис

    private final KafkaMessageService kafkaMessageService;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;
    private final KafkaTemplate<String, KafkaMessageDTO> kafkaTemplateDTO;

    @KafkaListener(topics = "${app.kafka.topicToRead}",
                   groupId = "${app.kafka.kafkaMessageGroupId}",
                   containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp) {

        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timeStamp);

     //   kafkaTemplate.send(topic, message);
        kafkaMessageService.add(message);
        send(new KafkaMessageDTO("CREATED"), "order-status-service");
        System.out.println("messages list has: " + kafkaMessageService.print());
    }

    @KafkaListener(topics = "${app.kafka.topicToWrite}",
                   groupId = "${app.kafka.kafkaMessageGroupId}",
                   containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void send(@Payload KafkaMessageDTO message,
                     @Header(value = KafkaHeaders.RECEIVED_TOPIC) String topic) {

        log.info("Received message: {}", message);
        log.info("Message: {}; Topic: {}", message, topic);

        kafkaTemplateDTO.send(topic, message);
    }
}
