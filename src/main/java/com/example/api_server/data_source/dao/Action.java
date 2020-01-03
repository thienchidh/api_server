package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.User;

public interface Action {
    User register(Account account);

    User login(Account account);

    boolean logout(Account account);
}
