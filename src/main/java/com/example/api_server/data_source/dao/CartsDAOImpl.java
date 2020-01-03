package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.ProductRepository;
import com.example.api_server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsDAOImpl implements BaseDAO<Product> {

    private ProductRepository repo;

    @Autowired
    public CartsDAOImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Product o) {
        repo.save(o);
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }
}
