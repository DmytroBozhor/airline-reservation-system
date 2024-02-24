package com.dmytrobozhor.airlinereservationservice.service;

import java.util.List;

public interface AbstractCrudService<T, ID> {

    //    TODO: generates a lot of queries. find a way do fix it
    List<T> findAll();

    T save(T entity);

    T deleteById(ID id);

    T findById(ID id);

    T updateById(ID id, T entity);

    T updateOrCreateById(ID id, T entity);

    List<T> saveAll(List<T> entities);

}
