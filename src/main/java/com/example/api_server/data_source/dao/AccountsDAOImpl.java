package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.AccountsRepository;
import com.example.api_server.model.Account;
import com.example.api_server.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountsDAOImpl implements AccountsDAO, ActionAccount {
    AccountsRepository repo;

    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return repo.findAll(pageable);
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
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Account o) {
        repo.delete(o);
    }

    @Override
    public User loginWithAccount(Account account) {

        // TODO
        return null;
    }

    @Override
    public User loginWithToken(String token) {

        // TODO
        return null;
    }

    @Override
    public boolean logout(String token) {
        return true;
    }

    @Override
    public User register(Account account) {
        // TODO
        return null;
    }
}