package com.app.messagingapplication.controller;

import com.app.messagingapplication.entity.UserAccount;
import com.app.messagingapplication.service.IUserAccountService;
import com.app.messagingapplication.utility.constants.ChannelID;
import com.app.messagingapplication.utility.custom_exceptions.InvalidDataException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountControllerTest {
    @InjectMocks
    private UserAccountController userAccountController;
    @Mock
    private IUserAccountService iUserAccountService;

    @Test
    public void testShouldBeAbleToCreateUserWithNickname() throws InvalidDataException {
        UserAccount expectedUserAccount = UserAccount.builder()
                .nickname("test")
                .firstname("firstname")
                .lastname("lastname")
                .build();
        when(iUserAccountService.createUserAccount(any())).thenReturn("User account created with nickname test");

        ResponseEntity<Object> actualUserAccount = userAccountController.createUser(
                "correlationID", "1.0.0", ChannelID.Web, "", expectedUserAccount);

        assertEquals(HttpStatus.OK, actualUserAccount.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToReturnExceptionForNicknameExists() throws InvalidDataException {
        UserAccount expectedUserAccount = UserAccount.builder()
                .nickname("test")
                .firstname("firstname")
                .lastname("lastname")
                .build();
        when(iUserAccountService.createUserAccount(any())).thenThrow(new InvalidDataException("nickname already exists"));

        ResponseEntity<Object> actualUserAccount = userAccountController.createUser(
                "correlationID", "1.0.0", ChannelID.Web, "", expectedUserAccount);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUserAccount.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToReturnHttpStatusForNotExistingApiVersion() throws InvalidDataException {
        UserAccount expectedUserAccount = UserAccount.builder()
                .nickname("test")
                .firstname("firstname")
                .lastname("lastname")
                .build();

        ResponseEntity<Object> actualUserAccount = userAccountController.createUser(
                "correlationID", "2.0.0", ChannelID.Web, "", expectedUserAccount);

        assertEquals(HttpStatus.NOT_IMPLEMENTED, actualUserAccount.getStatusCode());
    }

}
