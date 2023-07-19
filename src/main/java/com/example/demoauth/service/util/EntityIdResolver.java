package com.example.demoauth.service.util;


import com.example.demoauth.model.entity.LoggableEntity;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

@NoArgsConstructor
public class EntityIdResolver extends SimpleObjectIdResolver {

    private EntityManager entityManager;

    private Class<? extends EntityType> entityClass;


    public EntityIdResolver(EntityManager entityManager, Class<? extends EntityType> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey id) {
        Object resolved = super.resolveId(id);
        if (resolved == null) {
            resolved = entityManager.getReference(entityClass, id.key);
            bindItem(id, resolved);
        }
        return resolved;

    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new EntityIdResolver(this.entityManager, this.entityClass);
    }
}
