package com.example.api_server.controller;

import com.example.api_server.data_source.dao.CartsDAOImpl;
import com.example.api_server.model.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartManagerController {
    private static final Logger logger = Logger.getLogger(CartManagerController.class);

    private CartsDAOImpl cartsDAO;

    @Autowired
    public CartManagerController(CartsDAOImpl cartsDAO) {
        this.cartsDAO = cartsDAO;
    }

    @RequestMapping(
            value = "/updateCart",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateCart(@RequestHeader(name = "cookie", defaultValue = "") String cookie, @RequestBody Cart cart) {
        logger.info(cart);
        //TODO
        return new ResponseEntity<>(ResponseEntity.ok(cart), HttpStatus.OK);
    }
}
