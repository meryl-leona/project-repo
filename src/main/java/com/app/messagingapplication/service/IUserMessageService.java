package com.app.messagingapplication.service;

import com.app.messagingapplication.entity.UserMessage;
import com.app.messagingapplication.utility.custom_exceptions.InvalidDataException;

import java.util.List;
public interface IUserMessageService {

    String sendMessage(UserMessage userMessage) throws InvalidDataException;
    List<UserMessage> viewSentMessages(String sender);
    List<UserMessage> viewReceivedMessages(String receiver);
    List<UserMessage> viewMessagesFromUser(String sender, String receiver);

}
