package com.dmytrobozhor.airlinereservationservice.service.base;

import com.dmytrobozhor.airlinereservationservice.domain.BaseEntity;
import com.dmytrobozhor.airlinereservationservice.util.mappers.config.UpdatePartiallyMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public abstract class ServiceBase<T extends BaseEntity<ID>, ID extends Serializable>
        implements AbstractCrudService<T, ID> {

    private final JpaRepository<T, ID> repository;

    private final UpdatePartiallyMapper<T, ID> mapper;

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T deleteById(ID id) {
        var entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        repository.delete(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public T updateById(ID id, T entity) {
        return repository.findById(id)
                .map(persistedAirport -> mapper.updatePartially(persistedAirport, entity))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public T updateOrCreateById(ID id, T entity) {
        return repository.findById(id)
                .map(persistedAirport -> mapper.updatePartially(persistedAirport, entity))
                .orElseGet(() -> repository.save(entity));
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }
}
