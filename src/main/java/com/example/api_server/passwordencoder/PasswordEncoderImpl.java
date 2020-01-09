package com.example.api_server.passwordencoder;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordEncoderImpl extends BCryptPasswordEncoder {
}
