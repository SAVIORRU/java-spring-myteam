package com.example.demoauth.service;

import com.example.demoauth.model.entity.LoggableEntity;
import com.example.demoauth.model.repository.LoggableEntityRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
public class LoggableEntityService<T extends LoggableEntity<ID>, ID extends Serializable> {
    private final LoggableEntityRepository<T, ID> repository;
    private final ObjectMapper objectMapper;

    public boolean updateEntityById(ID id, T entity){
        Optional<T> existingEntity = repository.findById(id);
        entity.setLastUpdated(LocalDateTime.now());
        entity.setId(id);
        boolean isCreated = false;
        if (existingEntity.isPresent()){
            entity.setCreated(existingEntity.get().getCreated());
        }
        else{
            entity.setCreated(LocalDateTime.now());
            isCreated = true;
        }
        repository.save(entity);
        return  isCreated;
    }

    @SneakyThrows(JsonMappingException.class)
    public void patchEntityById(ID id, Object dto){
        T existingEntity = repository.findById(id).get();
        objectMapper.updateValue(existingEntity, dto);
        existingEntity.setLastUpdated(LocalDateTime.now());
        repository.save(existingEntity);

    }
    public void deleteEntityById(ID id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new NoSuchElementException();
        }
    }
    public Page<T> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T getEntityById(ID id){ return repository.findById(id).get(); }

    public void saveAll(List<T> entities){
        for (T entity : entities){
            entity.setLastUpdated(LocalDateTime.now());
            entity.setCreated(LocalDateTime.now());
        }
        repository.saveAll(entities);
    }

    public List<T> getEntityById(List<ID> ids){
        List<T> entities = new ArrayList<>();
        for (ID id: ids){
            Optional<T> entity = repository.findById(id);
            entity.ifPresent(entities::add);
        }
        if (entities.isEmpty()){ throw new NoSuchElementException(); }
        return entities;
    }
}
