package com.app.messenger.service;

import com.app.messenger.entity.UserAccount;
import com.app.messenger.repository.UserAccountRepository;
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
public class UserAccountServiceTest {
    @InjectMocks
    private UserAccountService userAccountService;
    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    public void testShouldBeAbleToCreateUserAccount() throws InvalidDataException {
        UserAccount expectedUserAccount = UserAccount.builder()
                .nickname("nickname")
                .firstname("firstname")
                .lastname("lastname")
                .build();
        when(userAccountRepository.save(any())).thenReturn(expectedUserAccount);

        String actualUserAccount = userAccountService.createNewAccount(expectedUserAccount);

        assertEquals("User account created with nickname nickname", actualUserAccount);
    }
}
