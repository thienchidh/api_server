package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.CartsRepository;
import com.example.api_server.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartsDAOImpl implements CartDAO {
    private CartsRepository repository;

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Cart o) {
        repository.save(o);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Cart o) {
        repository.delete(o);
    }
}
