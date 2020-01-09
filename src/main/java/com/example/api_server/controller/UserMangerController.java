package com.example.api_server.controller;

import com.example.api_server.data_source.dao.AccountsDAOImpl;
import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserMangerController {

    private static final Logger logger = Logger.getLogger(UserMangerController.class);

    private AccountsDAOImpl accountsDAO;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogin(@RequestBody Account account) {
        UserSession session = accountsDAO.login(account);
        return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/loginWithToken",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLoginWithToken(@RequestBody String token) {
        logger.info("token=" + token);
        UserSession session = accountsDAO.login(token);
        return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogout(@RequestBody String token) {
        logger.info("token=" + token);
        boolean isLogout = accountsDAO.logout(token);
        return isLogout ? new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK) : new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userRegister(@RequestBody Account account) {
        logger.info("account=" + account);
        UserSession session = accountsDAO.register(account);
        return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
    }
}