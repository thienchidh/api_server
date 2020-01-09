package com.example.api_server.controller;

import com.example.api_server.data_source.dao.CartsDAOImpl;
import com.example.api_server.model.Cart;
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

    private CartsDAOImpl cartsDAO;

    @RequestMapping(
            value = "/cart",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCart(@RequestBody String token) {
        // TODO
//        Optional<Cart> cart = cartsDAO.findById(id);
//        if (cart.isPresent()) {
//            return new ResponseEntity<>(ResponseEntity.ok(cart.get()), HttpStatus.OK);
//        }
        return new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/cart/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> updateCart(@RequestBody String token, @RequestBody Cart cart) {
        logger.info(cart.toString());
        //TODO
        return new ResponseEntity<>(ResponseEntity.ok(cart), HttpStatus.OK);
    }
}
