package com.example.api_server.data_source.dao;

import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Optional;

public interface UserSessionDAO extends BaseDAO<UserSession> {

    UserSession addAgeSession(@NonNull UserSession session);

    UserSession makeUserSession(Account saveAccount);

    UserSession makeUserSession(Account account, Date dateExpiredToken);

    Optional<UserSession> findUserSessionBy(String token);
}
