package com.example.api_server.data_source.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {

    List<T> findAll();

    Optional<T> findById(long id);

    void save(T o);

    void deleteById(long o);
}


