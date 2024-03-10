package com.dmytrobozhor.airlinereservationservice.service.base;

import com.dmytrobozhor.airlinereservationservice.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface AbstractCrudService<T extends BaseEntity<ID>, ID extends Serializable> {

    //    TODO: generates a lot of queries. find a way do fix it
    List<T> findAll();

    T save(T entity);

    T deleteById(ID id);

    T findById(ID id);

    T updateById(ID id, T entity);

    T updateOrCreateById(ID id, T entity);

    List<T> saveAll(List<T> entities);

}
