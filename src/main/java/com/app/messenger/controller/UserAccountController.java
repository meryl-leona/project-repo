package com.app.messenger.controller;

import com.app.messenger.entity.UserAccount;
import com.app.messenger.service.UserAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "Create user controller")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Operation(description = "Create user", summary = "New user")
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createUser(@RequestHeader @NotBlank String correlationId,
                                     @RequestHeader (defaultValue = "1.0.0", required = false) String version,
                                     @RequestHeader @Pattern(regexp = "Web|Android|iOS") String channel,
                                     @RequestHeader (required = false) String authorization,
                                     @RequestBody UserAccount userAccount) {
        try {
            log.info("Create user invoked with correlation ID {}, channel {} and api-version {}", correlationId, channel, version);
            return new ResponseEntity<>(userAccountService.createNewAccount(userAccount), HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Exception creating new user: " + exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}