package com.p3arl.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
@Slf4j
public class KafkaController {

    @Autowired
    private KafkaPublisher kafkaPublisher;
    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @PostMapping("publish")
    public String publishMessage(@RequestBody String data) {
        return kafkaPublisher.publish(topic, data);
    }
}
