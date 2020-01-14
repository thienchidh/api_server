package com.example.api_server.data_source.dao;

import com.example.api_server.data_source.repo.CartsRepository;
import com.example.api_server.helper.AuthenticationHelper;
import com.example.api_server.model.Cart;
import com.example.api_server.model.User;
import com.example.api_server.model.UserSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CartsDAOImpl implements CartsDAO {
    private static final Logger logger = LoggerFactory.getLogger(CartsDAOImpl.class);

    private AuthenticationHelper authentication;
    private CartsRepository cartsRepo;
    private UserSessionDAO userSessionDAO;

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
    public void saveAll(Iterable<Cart> iterable) {
        cartsRepo.saveAll(iterable);
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
    public boolean existsById(long id) {
        return cartsRepo.existsById(id);
    }

    @Override
    public boolean updateCart(String token, Cart cart) {
        if (authentication.isAliveToken(token)) {
            Optional<UserSession> optionalUserSession = userSessionDAO.findUserSessionBy(token);
            if (optionalUserSession.isPresent()) {
                UserSession session = optionalUserSession.get();
                Optional<Cart> optional = cartsRepo.findOne(Example.of(Cart.builder()
                        .user(session.getUser())
                        .build()
                ));
                if (optional.isPresent()) {
                    Cart cardDB = optional.get();
                    cardDB.setProducts(cart.getProducts());
                    save(cardDB);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Cart findCart(String token) {
        Optional<UserSession> optionalUserSession = userSessionDAO.findUserSessionBy(token);
        if (optionalUserSession.isPresent()) {
            UserSession session = optionalUserSession.get();
            User user = session.getUser();
            Optional<Cart> optional = cartsRepo.findOne(Example.of(Cart.builder()
                    .user(user)
                    .build()
            ));
            return optional.orElseGet(() -> cartsRepo.save(Cart.builder()
                    .user(user)
                    .products(List.of())
                    .build())
            );
        }
        return null;
    }
}
