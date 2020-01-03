package com.example.api_server.controller;

import com.example.api_server.data_source.dao.AccountsDAOImpl;
import com.example.api_server.model.Account;
import com.example.api_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMangerController {

    private AccountsDAOImpl accountsDAO;

    @Autowired
    public UserMangerController(AccountsDAOImpl accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    ResponseEntity<User> userLogin(@RequestBody Account account) {
        User user = accountsDAO.login(account);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogout(@RequestBody Account account) {
        boolean isLogout = accountsDAO.logout(account);
        return isLogout ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    ResponseEntity<User> userRegister(@RequestBody Account account) {
        User user = accountsDAO.register(account);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
