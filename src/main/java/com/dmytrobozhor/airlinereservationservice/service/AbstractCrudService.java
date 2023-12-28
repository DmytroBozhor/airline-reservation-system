package com.dmytrobozhor.airlinereservationservice.service;

import java.util.List;

public interface AbstractCrudService<T, ID> {

    List<T> findAll();

    T save(T entity);

    void deleteById(ID id);

    void delete(T entity);

    T findById(ID id);

    T updateById(ID id, T entity);

    T updateOrCreateById(ID id, T entity);
}
