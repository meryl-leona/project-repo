package com.app.messagingapplication.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserMessageRepositoryTest {

    @Captor
    private ArgumentCaptor<String> userMessageArgumentCaptor;

    @Test
    void testShouldBeAbleToFindAllMessagesBySender() {
        UserMessageRepository userMessageRepository = mock(UserMessageRepository.class);
        userMessageRepository.findAllBySender("senderNickname");
        verify(userMessageRepository).findAllBySender(userMessageArgumentCaptor.capture());
    }

    @Test
    void testShouldBeAbleToFindAllMessagesByReceiver() {
        UserMessageRepository userMessageRepository = mock(UserMessageRepository.class);
        userMessageRepository.findAllByReceiver("senderNickname");
        verify(userMessageRepository).findAllByReceiver(userMessageArgumentCaptor.capture());
    }

}
