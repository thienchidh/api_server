package com.example.api_server.controller;

import com.example.api_server.controller.rest.UserMangerController;
import com.example.api_server.controller.services.ProductServices;
import com.example.api_server.controller.services.UserServices;
import com.example.api_server.model.*;
import lombok.Setter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Setter(onMethod = @__({@Autowired}))
@TestPropertySource(locations = "classpath:application.properties")
@TestMethodOrder(value = MethodOrderer.Alphanumeric.class)
public class ProductsTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMangerController.class);
    private ProductServices productServices;
    private UserServices userServices;


    @Test
    void test01_addProduct() {
        Account accountUser = Account.builder()
                .username("user")
                .password("123456")
                .user(
                        User.builder()
                                .firstName("tran")
                                .lastName("chi")
                                .role(Role.IS_USER)
                                .address("address here")
                                .email("email@email.com")
                                .other("other")
                                .build()
                )
                .build();

        Account accountAdmin = Account.builder()
                .username("admin")
                .password("123456")
                .user(
                        User.builder()
                                .firstName("tran")
                                .lastName("chi")
                                .role(Role.IS_ADMIN)
                                .address("address here")
                                .email("email@email1.com")
                                .other("other")
                                .build()
                )
                .build();

        UserSession session = userServices.register(accountUser);
        Product product = Product.builder()
                .name("đây là tên tiếng việt có dấu")
                .imageLink("#")
                .price(0.5)
                .other("other")
                .build();
        UserSession session1 = userServices.register(accountAdmin);
        assertFalse(productServices.save(session.getToken(), product));
        assertTrue(productServices.save(session1.getToken(), product));
    }

    @Test
    void test02_removeProduct() {
        Account accountAdmin = Account.builder()
                .username("admin")
                .password("123456")
                .user(
                        User.builder()
                                .firstName("tran")
                                .lastName("chi")
                                .role(Role.IS_ADMIN)
                                .address("address here")
                                .email("email@email.com")
                                .other("other")
                                .build()
                )
                .build();
        UserSession session1 = userServices.login(accountAdmin);
        assertFalse(productServices.deleteById(session1.getToken(), -1));
    }

    @Test
    void test03_updateProduct() {
        Account accountAdmin = Account.builder()
                .username("admin")
                .password("123456")
                .user(
                        User.builder()
                                .firstName("tran")
                                .lastName("chi")
                                .role(Role.IS_ADMIN)
                                .email("email@email.com")
                                .address("address here")
                                .other("other")
                                .build()
                )
                .build();
        UserSession session1 = userServices.login(accountAdmin);
        Product product = productServices.findById(1).orElse(new Product());
        product.setName("1");
        assertTrue(productServices.save(session1.getToken(), product));
    }
}