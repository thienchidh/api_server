package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.model.Account;
import com.example.api_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountsDAOImpl implements BaseDAO<Account>, Action {
    AccountsRepository repo;

    @Autowired
    public AccountsDAOImpl(AccountsRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Account> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Account o) {
        repo.save(o);
    }

    @Override
    public void deleteById(long o) {
        repo.deleteById(o);
    }

    @Override
    public User login(Account account) {

        // TODO
        return null;
    }

    @Override
    public boolean logout(Account account) {

        // TODO
        return true;
    }

    @Override
    public User register(Account account) {
        // TODO
        return null;
    }
}