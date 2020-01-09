package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.data_source.repo.UserSessionRepository;
import com.example.api_server.model.Account;
import com.example.api_server.model.UserSession;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AccountsDAOImpl implements AccountsDAO, ActionAccount {

    private static final Logger logger = Logger.getLogger(AccountsDAOImpl.class);

    private AccountsRepository accountsRepo;
    private UserSessionRepository userSessionRepo;
    private PasswordEncoder passwordEncoder;

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
    public UserSession login(Account account) {
        Account probe = Account.builder().username(account.getUsername()).build();

        Optional<Account> optional = accountsRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            Account accountDB = optional.get();
            if (passwordEncoder.matches(account.getPassword() + accountDB.getSalt(), accountDB.getPassword())) {
                // login success! now, we make a token
                return makeUserSession(accountDB);
            }
        }
        return null;
    }

    @Override
    public UserSession login(String token) {

        UserSession probe = UserSession.builder().token(token).build();
        Optional<UserSession> optional = userSessionRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            UserSession session = optional.get();
            Date dateExpired = session.getDateExpired();
            Date now = Calendar.getInstance().getTime();

            if (dateExpired.after(now)) {
                return session;
            }
        }
        return null;
    }

    @Override
    public boolean logout(String token) {

        UserSession probe = UserSession.builder().token(token).build();
        Optional<UserSession> optional = userSessionRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            UserSession session = optional.get();
            Date dateExpired = session.getDateExpired();
            Date now = Calendar.getInstance().getTime();

            if (dateExpired.after(now)) {
                session.setDateExpired(now);
                userSessionRepo.save(session);
            }
            return true;
        }
        return false;
    }

    @Override
    public UserSession register(Account account) {

        Account probe = Account.builder().username(account.getUsername()).build();

        boolean isAccountExists = accountsRepo.exists(Example.of(probe));

        if (isAccountExists) return null;

        Date today = Calendar.getInstance().getTime();
        String salt = passwordEncoder.encode(today.toString());
        account.setSalt(salt);

        String hashedPassword = passwordEncoder.encode(account.getPassword() + salt);
        account.setPassword(hashedPassword);

        // save to datasource
        accountsRepo.save(account);

        return makeUserSession(account, today);
    }

    private UserSession makeUserSession(Account account) {
        return makeUserSession(account, Calendar.getInstance().getTime());
    }

    private UserSession makeUserSession(Account account, Date today) {

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date today30 = cal.getTime();

        String token = account.getUsername() + passwordEncoder.encode(account.getPassword());
        UserSession session = UserSession.builder().token(token).dateExpired(today30).user(account.getUser()).build();
        userSessionRepo.save(session);
        return session;
    }
}