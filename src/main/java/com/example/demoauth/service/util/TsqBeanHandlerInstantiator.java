package com.example.demoauth.service.util;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;

@Component
public class TsqBeanHandlerInstantiator extends SpringHandlerInstantiator {
    protected static final Logger logger = LoggerFactory.getLogger(TsqBeanHandlerInstantiator.class.getName());

    private final AutowireCapableBeanFactory beanFactory;

    private final EntityManager entityManager;

    private List<Class <? extends EntityType> > tsqEntities = new ArrayList<>();


    public TsqBeanHandlerInstantiator(AutowireCapableBeanFactory beanFactory, AutowireCapableBeanFactory beanFactory1, EntityManager entityManager) {
        super(beanFactory);
        this.beanFactory = beanFactory1;
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void init(){
        for (EntityType<?> entity : entityManager.getMetamodel().getEntities()){
            Class<? extends EntityType> entityClass = (Class<? extends EntityType>) entity.getJavaType();
            tsqEntities.add(entityClass);
        }
    }

    @Override
    public JsonDeserializer<?> deserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> implClass) {
        Class entityClass = annotated.getRawType();
        logger.debug("deserializerInstance : " + entityClass);
        return (JsonDeserializer<?>) this.beanFactory.createBean(implClass);
    }

    @Override
    public ObjectIdGenerator<?> objectIdGeneratorInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        Class entityClass = annotated.getRawType();
        logger.debug("objectIdGeneratorInstance : " + entityClass);
        return (ObjectIdGenerator<?>) this.beanFactory.createBean(implClass);
    }

    @Override
    public ObjectIdResolver resolverIdGeneratorInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        Class entityClass = annotated.getRawType();
        if (!tsqEntities.contains(entityClass)){
            return super.resolverIdGeneratorInstance(config, annotated, implClass);
        }
        logger.debug("resolverIdGeneratorInstance : " + entityClass);
        return new EntityIdResolver(entityManager, entityClass);
    }
}
