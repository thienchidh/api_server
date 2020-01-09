package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;

public interface ActionAccount {
    UserSession register(Account account);

    UserSession login(Account account);

    UserSession login(String token);

    boolean logout(String token);
}
