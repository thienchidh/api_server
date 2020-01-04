package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.ProductRepository;
import com.example.api_server.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repo.findAll(pageable);
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

    @Override
    public void delete(Product o) {
        repo.delete(o);
    }
}
