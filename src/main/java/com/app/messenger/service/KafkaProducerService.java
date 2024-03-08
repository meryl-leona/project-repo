package com.app.messenger.service;

import com.app.messenger.entity.UserMessage;

import com.app.messenger.utility.constants.MessagingKafkaConstants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UserMessage userMessage){
        log.info("Sending Kafka message on topic {} ", MessagingKafkaConstants.SEND_MESSAGE_TOPIC);
        kafkaTemplate.send(MessagingKafkaConstants.SEND_MESSAGE_TOPIC, userMessage.toString());
    }

}
