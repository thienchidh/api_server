package com.example.api_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String password;// hashed
    private String salt;
    private String token;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date dateCreated;

    private Role role;
}
