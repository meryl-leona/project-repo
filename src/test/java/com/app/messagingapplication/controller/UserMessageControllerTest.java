package com.app.messagingapplication.controller;

import com.app.messagingapplication.entity.UserMessage;
import com.app.messagingapplication.service.IUserMessageService;
import com.app.messagingapplication.utility.constants.ChannelID;
import com.app.messagingapplication.utility.custom_exceptions.InvalidDataException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMessageControllerTest {

    @InjectMocks
    private UserMessageController userMessageController;
    @Mock
    private IUserMessageService iUserMessageService;

    @Test
    public void testShouldBeAbleToSendMessage() throws InvalidDataException {
        UserMessage expectedUserMessage = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();
        when(iUserMessageService.sendMessage(any())).thenReturn("You cannot send a message to yourself");

        ResponseEntity<Object> actualUserMessage = userMessageController.sendMessageToUser(
                "correlationID", "1.0.0", ChannelID.Web, "", expectedUserMessage);

        assertEquals(HttpStatus.OK, actualUserMessage.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToReturnExceptionForInvalidSenderReceiver() throws InvalidDataException {
        UserMessage expectedUserMessage = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();
        when(iUserMessageService.sendMessage(any())).thenThrow(new InvalidDataException("Invalid Sender/Receiver"));

        ResponseEntity<Object> actualUserMessage = userMessageController.sendMessageToUser(
                "correlationID", "1.0.0", ChannelID.Web, "", expectedUserMessage);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualUserMessage.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToReturnHttpStatusForNotExistingApiVersion() throws InvalidDataException {
        UserMessage expectedUserMessage = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();

        ResponseEntity<Object> actualUserMessage = userMessageController.sendMessageToUser(
                "correlationID", "2.0.0", ChannelID.Web, "", expectedUserMessage);

        assertEquals(HttpStatus.NOT_IMPLEMENTED, actualUserMessage.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToFetchSentMessages() {
        UserMessage sentMessages = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();
        when(iUserMessageService.viewSentMessages(any())).thenReturn(Arrays.asList(sentMessages));

        ResponseEntity<Object> actualUserMessage = userMessageController.fetchAllSentMessages(
                "correlationID", "1.0.0", ChannelID.Web, "", "nickname");

        assertEquals(HttpStatus.OK, actualUserMessage.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToFetchReceivedMessages(){
        UserMessage receivedMessages = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();
        when(iUserMessageService.viewReceivedMessages(any())).thenReturn(Arrays.asList(receivedMessages));

        ResponseEntity<Object> actualUserMessage = userMessageController.fetchAllReceivedMessages(
                "correlationID", "1.0.0", ChannelID.Web, "", "nickname");

        assertEquals(HttpStatus.OK, actualUserMessage.getStatusCode());
    }

    @Test
    public void testShouldBeAbleToFetchReceivedMessagesFromParticularUser(){
        UserMessage receivedMessages = UserMessage.builder()
                .message("test")
                .sender("sender")
                .receiver("receiver")
                .createdTime(LocalDateTime.of(LocalDate.of(2024, 2, 2),
                        LocalTime.of(0,55,22,616385)))
                .build();
        when(iUserMessageService.viewMessagesFromUser(any(), any())).thenReturn(Arrays.asList(receivedMessages));

        ResponseEntity<Object> actualUserMessage = userMessageController.fetchReceivedMessagesFromNickname(
                "correlationID", "1.0.0", ChannelID.Web, "", "usernickname", "mynickname");

        assertEquals(HttpStatus.OK, actualUserMessage.getStatusCode());
    }

}
