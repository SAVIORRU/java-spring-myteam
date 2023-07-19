package com.example.demoauth.controller;

import com.example.demoauth.model.entity.LoggableEntity;
import com.example.demoauth.model.entity.Team;
import com.example.demoauth.model.payload.EntityRequest;
import com.example.demoauth.model.payload.dto.TeamDto;
import com.example.demoauth.service.LoggableEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public class LoggableEntityController <T extends LoggableEntity<ID>, ID extends Serializable> {

    private final LoggableEntityService<T, ID> entityService;


    public LoggableEntityController( LoggableEntityService<T, ID> entityService) {
        this.entityService = entityService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable ID id){
        return ResponseEntity.ok(entityService.getEntityById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getEntityById(@PathVariable List<ID> id){
        return ResponseEntity.ok(entityService.getEntityById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllEntities(Pageable pageable){
        return ResponseEntity.ok(entityService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> createEntities(@RequestBody EntityRequest<T> payload){
        entityService.saveAll(payload.getPayload());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable ID id){
        entityService.deleteEntityById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable ID id, @RequestBody T entity){
        if ( entityService.updateEntityById(id, entity) ){
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable ID id, @RequestBody Object dto){
        entityService.patchEntityById(id, dto);
        return ResponseEntity.ok().build();
    }

}
