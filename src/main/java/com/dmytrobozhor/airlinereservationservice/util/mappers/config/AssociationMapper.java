package com.dmytrobozhor.airlinereservationservice.util.mappers.config;

import com.dmytrobozhor.airlinereservationservice.domain.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AssociationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseEntity<Long>> T findAssociationEntity(Long entityIdentifier, @TargetType Class<T> entityClass) {
        if (entityIdentifier == null) return null;
        return Optional.ofNullable(entityManager.find(entityClass, entityIdentifier))
                .orElseThrow(() -> new EntityNotFoundException("No entity found by id " + entityIdentifier));
    }

    public Long getEntityIdentifier(BaseEntity<Long> entity) {
        if (entity == null) return null;
        return entity.getId();
    }

}
