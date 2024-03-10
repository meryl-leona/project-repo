package com.app.messagingapplication.controller;

import com.app.messagingapplication.entity.UserMessage;
import com.app.messagingapplication.service.IUserMessageService;
import com.app.messagingapplication.utility.constants.ChannelID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/message")
@Tag(name = "Message Controller")
public class UserMessageController {

    @Autowired
    IUserMessageService iUserMessageService;

    @Operation(description = "Send message", summary = "send message to another user identified by his/her nickname")
    @PostMapping(value = "/send")
    public ResponseEntity<Object> sendMessageToUser(@RequestHeader @NotBlank String correlationId,
                                                    @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                    @RequestHeader @NotBlank ChannelID channel,
                                                    @RequestHeader (required = false) String authorization,
                                                    @RequestBody UserMessage userMessage) {
        try {
            log.info("Send message invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);

            if ("1.0.0".equals(version))
                return new ResponseEntity<>(iUserMessageService.sendMessage(userMessage), HttpStatus.OK);
            return new ResponseEntity<>("",  HttpStatus.NOT_IMPLEMENTED);

        } catch (Exception exception) {
            log.error("Exception occurred sending message to user: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View sent messages", summary = "view all messages that I sent")
    @GetMapping(value = "/view/sent")
    public ResponseEntity<Object> fetchAllSentMessages(@RequestHeader @NotBlank String correlationId,
                                                       @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                       @RequestHeader @NotBlank ChannelID channel,
                                                       @RequestHeader (required = false) String authorization,
                                                       @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View sent messages invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);

            if ("1.0.0".equals(version))
                return new ResponseEntity<>(iUserMessageService.viewSentMessages(myNickname), HttpStatus.OK);
            return new ResponseEntity<>(Collections.emptyList(),  HttpStatus.NOT_IMPLEMENTED);

        } catch (Exception exception) {
            log.error("Exception occurred fetching all sent messages: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View received messages", summary = "view all messages that I received")
    @GetMapping(value = "/view/received")
    public ResponseEntity<Object> fetchAllReceivedMessages(@RequestHeader @NotBlank String correlationId,
                                                           @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                           @RequestHeader @NotBlank ChannelID channel,
                                                           @RequestHeader (required = false) String authorization,
                                                           @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View received messages invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);

            if ("1.0.0".equals(version))
                return new ResponseEntity<>(iUserMessageService.viewReceivedMessages(myNickname), HttpStatus.OK);
            return new ResponseEntity<>(Collections.emptyList(),  HttpStatus.NOT_IMPLEMENTED);

        } catch (Exception exception) {
            log.error("Exception occurred fetching all received messages: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View messages received from specific user", summary = "view all messages from a particular user")
    @GetMapping(value = "/view/received/from/{nickname}")
    public ResponseEntity<Object> fetchReceivedMessagesFromNickname(@RequestHeader @NotBlank String correlationId,
                                                                    @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                                    @RequestHeader @NotBlank ChannelID channel,
                                                                    @RequestHeader (required = false) String authorization,
                                                                    @PathVariable @NotBlank String nickname,
                                                                    @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View messages received from specific user invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);

            if ("1.0.0".equals(version))
                return new ResponseEntity<>(iUserMessageService.viewMessagesFromUser(nickname, myNickname), HttpStatus.OK);
            return new ResponseEntity<>(Collections.emptyList(),  HttpStatus.NOT_IMPLEMENTED);

        } catch (Exception exception) {
            log.error("Exception occurred fetching messages received from specific user : " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
