package com.app.messenger.service;

import com.app.messenger.entity.UserAccount;
import com.app.messenger.utility.exception.InvalidDataException;

public interface IUserAccountService {
    String createUserAccount(UserAccount userAccount) throws InvalidDataException;

}
