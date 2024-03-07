package com.app.messenger.service;

import com.app.messenger.entity.UserAccount;
import com.app.messenger.repository.UserAccountRepository;
import com.app.messenger.utility.exception.InvalidDataException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public String createNewAccount(UserAccount userAccount) throws InvalidDataException {
        boolean valid = validateUserInformation(userAccount);
        try {
            if (valid) {
                UserAccount saveUserAccountResult = userAccountRepository.save(userAccount);
                return "User account created with nickname " + saveUserAccountResult.getNickname();
            }
            throw new InvalidDataException("Nickname cannot be empty");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new InvalidDataException("Nickname " + userAccount.getNickname() + " already exists");
        }
    }

    private boolean validateUserInformation (UserAccount userAccount) {
        return !StringUtils.isEmpty(userAccount.getNickname());
    }
}
