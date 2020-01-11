package com.example.api_server.data_source.dao;

import com.example.api_server.model.Cart;

public interface CartsDAO extends BaseDAO<Cart> {
    boolean updateCart(String token, Cart cart);

    Cart findCart(String token);
}
