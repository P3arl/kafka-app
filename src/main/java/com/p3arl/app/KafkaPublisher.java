package com.p3arl.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String publish(String topic, String data) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, data);
        SendResult<String, String> sendResult;
        try {
            sendResult = send.get();
            log.info("sendResult| Partition: {}", sendResult.getProducerRecord().partition());
            return sendResult.getRecordMetadata().toString();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error: {}", e.getMessage());
            Thread.currentThread().interrupt();
            return "Error sending message to kafka: " + e.getMessage();
        }
    }
}
