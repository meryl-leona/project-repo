package com.app.messagingapplication.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountRepositoryTest {

    @Captor
    private ArgumentCaptor<String> userAccountArgumentCaptor;

    @Test
    void testShouldBeAbleToGetUserAccount() {
        UserAccountRepository userAccountRepository = mock(UserAccountRepository.class);
        userAccountRepository.findByNickname("nickname");
        verify(userAccountRepository).findByNickname(userAccountArgumentCaptor.capture());
    }

}
