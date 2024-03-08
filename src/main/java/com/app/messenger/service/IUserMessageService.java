package com.app.messenger.service;

import com.app.messenger.entity.UserMessage;
import com.app.messenger.utility.exception.InvalidDataException;

import java.util.List;

public interface IUserMessageService {

    String sendMessage(UserMessage userMessage) throws InvalidDataException;
    List<UserMessage> viewSentMessages(String sender);
    List<UserMessage> viewReceivedMessages(String receiver);
    List<UserMessage> viewMessagesFromUser(String sender, String receiver);

}
