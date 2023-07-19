package com.example.demoauth.model.repository;

import com.example.demoauth.model.entity.LoggableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface LoggableEntityRepository<T extends LoggableEntity<ID>, ID extends Serializable>  extends JpaRepository<T, ID> {
}
