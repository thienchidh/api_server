package com.example.api_server.controller.rest;

import com.example.api_server.controller.services.CartsServices;
import com.example.api_server.model.Cart;
import com.example.api_server.model.MyRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CartManagerController {
    private static final Logger logger = LoggerFactory.getLogger(CartManagerController.class);

    private CartsServices cartsServices;

    @RequestMapping(
            value = "/cart",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> findCart(@RequestBody String token) {
        Cart cart = cartsServices.findCart(token);
        if (cart != null) {
            return new ResponseEntity<>(ResponseEntity.ok(cart), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/cart/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateCart(@RequestBody MyRequest<Cart> requestBody) {
        String token = requestBody.getToken();
        Cart cart = requestBody.getData();
        boolean isUpdated = cartsServices.updateCart(token, cart);
        if (isUpdated) {
            return new ResponseEntity<>(ResponseEntity.ok(cart), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }
}
