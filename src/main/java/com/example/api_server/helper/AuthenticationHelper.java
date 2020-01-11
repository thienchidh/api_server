package com.example.api_server.helper;

import com.example.api_server.model.Account;
import org.springframework.lang.NonNull;

public interface AuthenticationHelper {
    String createSalt();

    String makePassword(@NonNull Account account, @NonNull String salt);

    String makePassword(@NonNull String passwordNonHash, @NonNull String salt);

    String makeToken(@NonNull Account account);

    boolean isLoginSuccess(@NonNull Account accountClient, @NonNull Account accountDB);

    boolean isValidToken(@NonNull String token);

    boolean isAliveToken(@NonNull String token);

    boolean isTokenAdmin(@NonNull String token);

    boolean isTokenUser(@NonNull String token);
}

