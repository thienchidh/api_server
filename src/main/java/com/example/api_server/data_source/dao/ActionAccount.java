package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.User;

public interface ActionAccount {
    User register(Account account);

    User loginWithAccount(Account account);

    User loginWithToken(String token);

    boolean logout(String token);
}
