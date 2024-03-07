package com.app.messenger.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "TopicSendMessage")
    public void consume(String userMessage) {
        log.info("Kafka message received {} ", userMessage);
    }
}
