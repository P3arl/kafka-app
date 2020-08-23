package com.p3arl.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaReceiver {

    @Autowired
    private KafkaPublisher kafkaPublisher;
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void receive(String data) {
        log.info("Received message: {}", data);
        kafkaPublisher.publish(topic, data);
    }
}
