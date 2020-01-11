package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.helper.AuthenticationHelper;
import com.example.api_server.model.Account;
import com.example.api_server.model.User;
import com.example.api_server.model.UserSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class AccountsDAOImpl implements AccountsDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountsDAOImpl.class);

    private AccountsRepository accountsRepo;
    private AuthenticationHelper authenticationHelper;
    private UserSessionDAO userSessionDAO;

    @Override
    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountsRepo.findAll(example);
    }

    @Override
    public List<Account> findAll() {
        return accountsRepo.findAll();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountsRepo.findAll(pageable);
    }

    @Override
    public Optional<Account> findById(long id) {
        return accountsRepo.findById(id);
    }

    @Override
    public void save(Account o) {
        accountsRepo.save(o);
    }

    @Override
    public void deleteById(long id) {
        accountsRepo.deleteById(id);
    }

    @Override
    public void delete(Account o) {
        accountsRepo.delete(o);
    }

    @Override
    public UserSession login(@NonNull Account accountClient) {
        Account probe = Account.builder().username(accountClient.getUsername()).build();

        Optional<Account> optional = accountsRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            Account accountDB = optional.get();
            if (authenticationHelper.isLoginSuccess(accountClient, accountDB)) {
                return userSessionDAO.makeUserSession(accountDB);
            }
        }
        return null;
    }

    @Override
    public UserSession login(@NonNull String token) {

        Optional<UserSession> optional = userSessionDAO.findUserSessionBy(token);

        return optional.map(session -> userSessionDAO.addAgeSession(session)).orElse(null);
    }

    @Override
    public boolean logout(@NonNull String token) {

        Optional<UserSession> optional = userSessionDAO.findUserSessionBy(token);

        if (optional.isPresent()) {
            UserSession session = optional.get();
            Date today = Calendar.getInstance().getTime();

            if (session.getDateExpired().after(today)) {
                session.setDateExpired(today);
                userSessionDAO.save(session);
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean changePassword(@NonNull Account accountClient) {
        Account probe = Account.builder().username(accountClient.getUsername()).build();

        Optional<Account> optional = accountsRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            Account accountDB = optional.get();
            if (authenticationHelper.isLoginSuccess(accountClient, accountDB)) {
                // update new password to datasource account
                accountDB.setPassword(accountClient.getNewPassword());
                saveAccount(accountDB);

                // logout all session of this user ?
                logoutAllSessionOfUser(accountDB.getUser().getId());
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public UserSession register(@NonNull Account account) {
        if (account.getUser() == null) return null;

        Account probe = Account.builder().username(account.getUsername()).build();

        boolean isAccountExists = accountsRepo.exists(Example.of(probe));
        return isAccountExists ? null : userSessionDAO.makeUserSession(saveAccount(account));
    }

    @Transactional
    @Override
    public void logoutAllSessionOfUser(Long userId) {
        UserSession probe = UserSession.builder()
                .user(User.builder()
                        .id(userId)
                        .build()
                )
                .build();
        List<UserSession> userSessions = userSessionDAO.findAll(Example.of(probe));
        Date today = Calendar.getInstance().getTime();
        for (UserSession e : userSessions) {
            if (e.getDateExpired().after(today)) {
                e.setDateExpired(today);
                userSessionDAO.save(e);
            }
        }
    }

    private Account saveAccount(@NonNull Account account) {
        String salt = authenticationHelper.createSalt();
        account.setSalt(salt);

        String hashedPassword = authenticationHelper.makePassword(account, salt);
        account.setPassword(hashedPassword);

        // save to datasource
        return accountsRepo.save(account);
    }
}