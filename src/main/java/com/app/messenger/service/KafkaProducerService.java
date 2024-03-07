package com.app.messenger.service;

import com.app.messenger.entity.UserMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final String TOPIC_SEND_MESSAGE = "TopicSendMessage";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UserMessage userMessage){
        log.info("Sending Kafka message on topic {} ", TOPIC_SEND_MESSAGE);
        kafkaTemplate.send(TOPIC_SEND_MESSAGE, userMessage.toString());
    }
}
