package com.example.api_server.controller;

import com.example.api_server.data_source.dao.AccountsDAOImpl;
import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.data_source.repo.UserSessionRepository;
import com.example.api_server.model.Account;
import com.example.api_server.model.Role;
import com.example.api_server.model.User;
import com.example.api_server.model.UserSession;
import com.google.gson.Gson;
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

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Setter(onMethod = @__({@Autowired}))
@TestPropertySource(locations = "classpath:application.properties")
@TestMethodOrder(value = MethodOrderer.Alphanumeric.class)
public class ActionsTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMangerController.class);
    private AccountsDAOImpl accountsDAO;
    private AccountsRepository accountsRepo;
    private UserSessionRepository sessionRepo;

    @Test
    void test01_UserRegister() {
        accountsRepo.deleteAll();
        assertEquals(0, accountsRepo.count());
        String jsonUser = "{\"username\":\"thienchidh\",\"password\":\"123456\",\"user\":{\"name\":\"Trần Thiện Chí\",\"address\":\"Địa Chỉ\",\"other\":\"Khác\"}}";
        Gson gson = new Gson();
        accountsDAO.register(gson.fromJson(jsonUser, Account.class));
        assertEquals(1, accountsRepo.count());
        accountsDAO.register(gson.fromJson(jsonUser, Account.class));
        assertEquals(1, accountsRepo.count());
        String jsonAdmin = "{\"username\":\"admin\",\"password\":\"123456\",\"role\":\"IS_ADMIN\",\"user\":{\"name\":\"Trần Thiện Chí\",\"address\":\"Địa Chỉ\",\"other\":\"Khác\"}}";
        accountsDAO.register(gson.fromJson(jsonAdmin, Account.class));
        assertEquals(2, accountsRepo.count());
    }

    @Test
    void test02_UserLogin() {
        Account account = Account.builder().username("thienchidh").password("123456").build();
        UserSession session1 = accountsDAO.login(account);
        UserSession session2 = accountsDAO.login(session1.getToken());
        UserSession session3 = accountsDAO.login(account);

        assertEquals(session1.getUser(), session2.getUser());
        assertEquals(session1.getToken(), session2.getToken());
        assertNotEquals(session1.getToken(), session3.getToken());
        assertNotEquals(session1.getDateExpired(), session2.getDateExpired());
        assertNotEquals(session1.getDateExpired(), session3.getDateExpired());

        assertTrue(session1.getDateExpired().after(new Date()));
        assertTrue(session2.getDateExpired().after(new Date()));
        assertTrue(session3.getDateExpired().after(new Date()));
    }

    @Test
    void test03_UserChangePassword() {

    }

    @Test
    void test04_UserLogout() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; ++i) {
                Account account = Account.builder()
                        .username("username" + i + new Random().nextFloat())
                        .password("123456")
                        .role(Role.IS_USER)
                        .user(
                                User.builder()
                                        .name("username" + i)
                                        .build()
                        ).build();
                UserSession register = accountsDAO.register(account);
                logger.info(account.getUsername());
                logger.info(register.getUser().getName());
            }
        });

        for (var e : accountsRepo.findAll()) logger.info(e.getUser().getName());

        for (var e : sessionRepo.findAll()) {
            if (new Date().before(e.getDateExpired())) {
                boolean logout = accountsDAO.logout(e.getToken());
                assertTrue(logout);
            }
        }
    }
}
