package com.example.api_server.controller.services;

import com.example.api_server.data_source.dao.ProductDAO;
import com.example.api_server.helper.AuthenticationHelper;
import com.example.api_server.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServices {
    private ProductDAO productDAO;
    private AuthenticationHelper authenticationHelper;

    public <S extends Product> List<S> findAll(Example<S> example) {
        return productDAO.findAll(example);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    public Optional<Product> findById(long id) {
        return productDAO.findById(id);
    }

    public boolean save(@NonNull String token, @NonNull Product o) {
        if (authenticationHelper.isTokenAdmin(token)) {
            productDAO.save(o);
            return true;
        }
        return false;
    }

    public boolean saveAll(@NonNull String token, @NonNull List<Product> o) {
        if (authenticationHelper.isTokenAdmin(token)) {
            productDAO.saveAll(o);
            return true;
        }
        return false;
    }

    public boolean deleteById(@NonNull String token, @NonNull long id) {
        if (authenticationHelper.isTokenAdmin(token) && productDAO.existsById(id)) {
            productDAO.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(@NonNull String token, @NonNull Product o) {
        if (authenticationHelper.isTokenAdmin(token)) {
            productDAO.delete(o);
            return true;
        }
        return false;
    }
}
