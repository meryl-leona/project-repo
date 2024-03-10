package com.app.messagingapplication.controller;

import com.app.messagingapplication.entity.UserAccount;
import com.app.messagingapplication.service.IUserAccountService;
import com.app.messagingapplication.utility.constants.ChannelID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotBlank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
public class UserAccountController {

    @Autowired
    IUserAccountService iUserAccountService;

    @Operation(description = "Create user", summary = "create new account by providing my nickname")
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createUser(@RequestHeader @NotBlank String correlationId,
                                     @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                     @RequestHeader @NotBlank ChannelID channel,
                                     @RequestHeader (required = false) String authorization,
                                     @RequestBody UserAccount userAccount) {
        try {
            log.info("Create user invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);

            if ("1.0.0".equals(version))
                return new ResponseEntity<>(iUserAccountService.createUserAccount(userAccount), HttpStatus.OK);
            return new ResponseEntity<>("",  HttpStatus.NOT_IMPLEMENTED);

        } catch (Exception exception) {
            log.error("Exception creating new user: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}