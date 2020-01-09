package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import org.springframework.lang.NonNull;

public interface ActionAccount {
    UserSession register(@NonNull Account account);

    UserSession login(@NonNull Account account);

    UserSession login(@NonNull String token);

    boolean logout(@NonNull String token);

    boolean changePassword(Account account);

//    boolean changePassword(UserSession session, Account account);
}
