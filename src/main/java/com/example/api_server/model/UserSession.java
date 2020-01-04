package com.example.api_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER_SESSIONS")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    @Column(unique = true)
    private String token;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date dateExpired;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private User user;
}

