package com.app.messenger.service;

import com.app.messenger.entity.UserMessage;
import com.app.messenger.repository.UserMessageRepository;
import com.app.messenger.utility.exception.InvalidDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUserMessageServiceTest {
    @InjectMocks
    private UserMessageService userMessageServiceImpl;
    @Mock
    private UserMessageRepository userMessageRepository;
    @Mock
    private KafkaProducerService kafkaProducerService;

    @Test
    public void testShouldBeAbleToCreateUserAccount() throws InvalidDataException {
        UserMessage expectedUserMessage = UserMessage.builder()
                .id(1)
                .message("message")
                .sender("senderNickname")
                .receiver("receiverNickname")
                .build();
        when(userMessageRepository.save(any())).thenReturn(expectedUserMessage);

        String actualUserMessage = userMessageServiceImpl.sendMessage(expectedUserMessage);

        assertEquals("Message sent to receiverNickname", actualUserMessage);
    }
}
