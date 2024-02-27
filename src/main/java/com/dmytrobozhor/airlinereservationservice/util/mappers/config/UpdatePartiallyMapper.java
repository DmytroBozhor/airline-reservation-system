package com.dmytrobozhor.airlinereservationservice.util.mappers.config;

import com.dmytrobozhor.airlinereservationservice.domain.BaseEntity;

import java.io.Serializable;

public interface UpdatePartiallyMapper<T extends BaseEntity<ID>, ID extends Serializable> {
    T updatePartially(T persistedEntity, T entity);
}
