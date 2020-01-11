package com.example.api_server.controller.services;

import com.example.api_server.data_source.dao.CartsDAOImpl;
import com.example.api_server.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartsServices {
    private CartsDAOImpl cartsDAO;

    public <S extends Cart> List<S> findAll(Example<S> example) {
        return cartsDAO.findAll(example);
    }

    public List<Cart> findAll() {
        return cartsDAO.findAll();
    }

    public Page<Cart> findAll(Pageable pageable) {
        return cartsDAO.findAll(pageable);
    }

    public Optional<Cart> findById(long id) {
        return cartsDAO.findById(id);
    }

    public void save(Cart o) {
        cartsDAO.save(o);
    }

    public void deleteById(long id) {
        cartsDAO.deleteById(id);
    }

    public void delete(Cart o) {
        cartsDAO.delete(o);
    }

    public boolean updateCart(String token, Cart cart) {
        return cartsDAO.updateCart(token, cart);
    }

    public Cart findCart(String token) {
        return cartsDAO.findCart(token);
    }
}
