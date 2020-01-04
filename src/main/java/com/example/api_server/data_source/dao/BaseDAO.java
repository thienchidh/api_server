package com.example.api_server.data_source.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(long id);

    void save(T o);

    void deleteById(long id);

    void delete(T o);
}


