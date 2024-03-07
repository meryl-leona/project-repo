package com.app.messenger.controller;

import com.app.messenger.entity.UserMessage;
import com.app.messenger.service.UserMessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Slf4j
@RestController
@RequestMapping("/message")
@Tag(name = "Message Controller")
public class UserMessageController {

    @Autowired
    UserMessageService userMessageService;

    @Operation(description = "Send message", summary = "New message")
    @PostMapping(value = "/send")
    public ResponseEntity<Object> sendMessageToUser(@RequestHeader @NotBlank String correlationId,
                                                    @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                    @RequestHeader @Pattern(regexp = "Web|Android|iOS") String channel,
                                                    @RequestHeader (required = false) String authorization,
                                                    @RequestBody UserMessage userMessage) {
        try {
            log.info("Send message invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);
            return new ResponseEntity<>(userMessageService.sendMessage(userMessage), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exception occurred sending message to user: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View sent messages", summary = "Sent messages")
    @GetMapping(value = "/view/sent")
    public ResponseEntity<Object> fetchAllSentMessages(@RequestHeader @NotBlank String correlationId,
                                                       @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                       @RequestHeader @Pattern(regexp = "Web|Android|iOS") String channel,
                                                       @RequestHeader (required = false) String authorization,
                                                       @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View sent messages invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);
            return new ResponseEntity<>(userMessageService.viewSentMessages(myNickname), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exception occurred fetching all sent messages: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View received messages", summary = "Received messages")
    @GetMapping(value = "/view/received")
    public ResponseEntity<Object> fetchAllReceivedMessages(@RequestHeader @NotBlank String correlationId,
                                                           @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                           @RequestHeader @Pattern(regexp = "Web|Android|iOS") String channel,
                                                           @RequestHeader (required = false) String authorization,
                                                           @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View received messages invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);
            return new ResponseEntity<>(userMessageService.viewReceivedMessages(myNickname), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exception occurred fetching all received messages: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "View messages received from specific user", summary = " Messages from user ID")
    @GetMapping(value = "/view/receivedfrom/{userNickname}")
    public ResponseEntity<Object> fetchReceivedMessagesFromNickname(@RequestHeader @NotBlank String correlationId,
                                                                    @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                                                    @RequestHeader @Pattern(regexp = "Web|Android|iOS") String channel,
                                                                    @RequestHeader (required = false) String authorization,
                                                                    @PathVariable @NotBlank String userNickname,
                                                                    @RequestHeader @NotBlank String myNickname) {
        try {
            log.info("View messages received from specific user invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);
            return new ResponseEntity<>(userMessageService.viewMessagesFromUser(userNickname, myNickname), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exception occurred fetching messages received from specific user : " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
