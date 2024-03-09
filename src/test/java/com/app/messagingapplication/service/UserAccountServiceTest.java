package com.app.messagingapplication.service;

import com.app.messagingapplication.entity.UserAccount;
import com.app.messagingapplication.repository.UserAccountRepository;
import com.app.messagingapplication.utility.custom_exceptions.InvalidDataException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {
    @InjectMocks
    private UserAccountService userAccountService;
    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    public void testShouldBeAbleToCreateUserAccount() throws InvalidDataException {
        UserAccount expectedUserAccount = UserAccount.builder()
                .nickname("test")
                .firstname("firstname")
                .lastname("lastname")
                .build();
        when(userAccountRepository.save(any())).thenReturn(expectedUserAccount);

        String actualUserAccount = userAccountService.createUserAccount(expectedUserAccount);

        assertEquals("User account created with nickname test", actualUserAccount);
    }

}
