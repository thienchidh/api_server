package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.CartsRepository;
import com.example.api_server.helper.AuthenticationHelper;
import com.example.api_server.model.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartsDAOImpl implements CartsDAO {
    private AuthenticationHelper authentication;
    private CartsRepository cartsRepo;

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example) {
        return cartsRepo.findAll(example);
    }

    @Override
    public List<Cart> findAll() {
        return cartsRepo.findAll();
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return cartsRepo.findAll(pageable);
    }

    @Override
    public Optional<Cart> findById(long id) {
        return cartsRepo.findById(id);
    }

    @Override
    public void save(Cart o) {
        cartsRepo.save(o);
    }

    @Override
    public void deleteById(long id) {
        cartsRepo.deleteById(id);
    }

    @Override
    public void delete(Cart o) {
        cartsRepo.delete(o);
    }

    @Override
    public boolean updateCart(String token, Cart cart) {
        if (authentication.isAliveToken(token)) {
            //TODO
            /*Long userId = cart.getUser().getId();

            return cartsRepo.save();*/
        }
        return false;
    }

    @Override
    public Cart findCart(String token) {
        return null;
    }
}
