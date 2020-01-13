package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.UserRepository;
import com.example.api_server.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDAOImpl implements UserDAO {

    private UserRepository repository;

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void save(User o) {
        repository.save(o);
    }

    @Override
    public void saveAll(Iterable<User> iterable) {
        repository.saveAll(iterable);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(User o) {
        repository.delete(o);
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }
}