package com.example.api_server.controller;

import com.example.api_server.controller.services.CartsServices;
import com.example.api_server.controller.services.UserServices;
import com.example.api_server.model.*;
import lombok.Setter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Setter(onMethod = @__({@Autowired}))
@TestPropertySource(locations = "classpath:application.properties")
@TestMethodOrder(value = MethodOrderer.Alphanumeric.class)
public class CartTest {

    private UserServices userServices;
    private CartsServices cartsServices;


    @Test
    void testCart01_findCard() {

        Account account = Account.builder()
                .username("thienchidh")
                .password("123456")
                .role(Role.IS_USER)
                .user(
                        User.builder()
                                .name("thienchidh")
                                .address("address here")
                                .other("other")
                                .build()
                )
                .build();
        UserSession session = userServices.register(account);
        String token = session.getToken();

        Cart cart = cartsServices.findCart(token);
        assertNotNull(cart);
        assertNotNull(cart.getProducts());
        assertTrue(cart.getProducts().isEmpty());

//        List<Product> products = new ArrayList<>();
//        products.add(Product.builder()
//                .name("1")
//                .build()
//        );


    }
}