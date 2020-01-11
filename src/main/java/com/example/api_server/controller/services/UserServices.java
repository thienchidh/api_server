package com.example.api_server.controller.services;

import com.example.api_server.data_source.dao.Authentication;
import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {
    private Authentication authentication;

    public UserSession register(Account account) {
        return authentication.register(account);
    }

    public UserSession login(Account account) {
        return authentication.login(account);
    }

    public UserSession login(String token) {
        return authentication.login(token);
    }

    public boolean logout(String token) {
        return authentication.logout(token);
    }

    public boolean changePassword(Account account) {
        return authentication.changePassword(account);
    }

    public void logoutAllSessionOfUser(Long userId) {
        authentication.logoutAllSessionOfUser(userId);
    }
}
