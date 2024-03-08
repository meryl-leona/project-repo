package com.app.messagingapplication.utility.kafka;

import com.app.messagingapplication.utility.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = KafkaConstants.SEND_MESSAGE_TOPIC)
    public void consume(String userMessage) {
        log.info("Kafka message received {} ", userMessage);
    }

}
