package com.app.messagingapplication.utility.kafka;

import com.app.messagingapplication.entity.UserMessage;

import com.app.messagingapplication.utility.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(UserMessage userMessage){
        log.info("Sending Kafka message on topic {} ", KafkaConstants.SEND_MESSAGE_TOPIC);
        kafkaTemplate.send(KafkaConstants.SEND_MESSAGE_TOPIC, userMessage.toString());
    }

}
