package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import org.springframework.lang.NonNull;

public interface Authentication {
    UserSession register(@NonNull Account account);

    UserSession login(@NonNull Account account);

    UserSession login(@NonNull String token);

    boolean logout(@NonNull String token);

    boolean changePassword(@NonNull Account account);

    void logoutAllSessionOfUser(Long userId);

//    boolean changePassword(UserSession session, Account account);
}
