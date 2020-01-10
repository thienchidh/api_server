package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.data_source.repo.UserSessionRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountsDAOImpl implements AccountsDAO, ActionAccount {

    private static final Logger logger = LoggerFactory.getLogger(AccountsDAOImpl.class);

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
    public UserSession login(@NonNull Account accountClient) {
        Account probe = Account.builder().username(accountClient.getUsername()).build();

        Optional<Account> optional = accountsRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            Account accountDB = optional.get();
            if (isLoginSuccess(accountClient, accountDB)) {
                return makeUserSession(accountDB);
            }
        }
        return null;
    }

    @Override
    public UserSession login(@NonNull String token) {

        Optional<UserSession> optional = findUserSessionBy(token);

        if (optional.isPresent()) {
            UserSession session = optional.get();
            Date today = Calendar.getInstance().getTime();

            if (session.getDateExpired().after(today)) {
                session.setDateExpired(getAfterDate());
                return userSessionRepo.save(session);
            }
        }
        return null;
    }

    @Override
    public boolean logout(@NonNull String token) {

        Optional<UserSession> optional = findUserSessionBy(token);

        if (optional.isPresent()) {
            UserSession session = optional.get();
            Date today = Calendar.getInstance().getTime();

            if (session.getDateExpired().after(today)) {
                session.setDateExpired(today);
                userSessionRepo.save(session);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(@NonNull Account accountClient) {
        Account probe = Account.builder().username(accountClient.getUsername()).build();

        Optional<Account> optional = accountsRepo.findOne(Example.of(probe));

        if (optional.isPresent()) {
            Account accountDB = optional.get();
            if (isLoginSuccess(accountClient, accountDB)) {
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

    @Override
    public UserSession register(@NonNull Account account) {
        if (account.getUser() == null) return null;

        Account probe = Account.builder().username(account.getUsername()).build();

        boolean isAccountExists = accountsRepo.exists(Example.of(probe));
        return isAccountExists ? null : makeUserSession(saveAccount(account));
    }

    private boolean isLoginSuccess(@NonNull Account accountClient, Account accountDB) {
        return passwordEncoder.matches(accountClient.getPassword() + accountDB.getSalt(), accountDB.getPassword());
    }

    private Account saveAccount(@NonNull Account account) {
        String salt = createSalt();
        account.setSalt(salt);

        String hashedPassword = makePassword(account, salt);
        account.setPassword(hashedPassword);

        // save to datasource
        return accountsRepo.save(account);
    }

    private Optional<UserSession> findUserSessionBy(String token) {
        UserSession probe = UserSession.builder().token(token).build();
        return userSessionRepo.findOne(Example.of(probe));
    }

    private void logoutAllSessionOfUser(Long id) {
        UserSession probe = UserSession.builder()
                .user(User.builder()
                        .id(id)
                        .build()
                )
                .build();
        List<UserSession> userSessions = userSessionRepo.findAll(Example.of(probe));
        Date today = Calendar.getInstance().getTime();
        for (UserSession e : userSessions) {
            if (e.getDateExpired().after(today)) {
                e.setDateExpired(today);
            }
        }
        userSessionRepo.saveAll(userSessions);
    }

    private UserSession makeUserSession(Account account) {
        Date dateExpiredToken = getAfterDate();

        return makeUserSession(account, dateExpiredToken);
    }

    private Date getAfterDate() {
        final int amountDefault = 30;
        return getAfterDate(amountDefault);
    }

    private Date getAfterDate(int amount) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    private UserSession makeUserSession(Account account, Date dateExpiredToken) {

        return userSessionRepo.save(UserSession.builder()
                .token(makeToken(account))
                .dateExpired(dateExpiredToken)
                .user(account.getUser())
                .build()
        );
    }

    private String createSalt() {
        Date today = Calendar.getInstance().getTime();
        return passwordEncoder.encode(today.toString());
    }

    private String makePassword(@NonNull Account account, String salt) {
        return passwordEncoder.encode(account.getPassword() + salt);
    }

    private String makeToken(@NonNull Account account) {
        return account.getUsername() + passwordEncoder.encode(account.getPassword());
    }
}