package com.p3arl.app;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableKafka
public class KafkaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAppApplication.class, args);
    }

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(topic)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }
}
