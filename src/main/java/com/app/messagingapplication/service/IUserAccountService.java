package com.app.messagingapplication.service;

import com.app.messagingapplication.entity.UserAccount;
import com.app.messagingapplication.utility.custom_exceptions.InvalidDataException;
public interface IUserAccountService {

    String createUserAccount(UserAccount userAccount) throws InvalidDataException;

}
