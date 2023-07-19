package com.example.demoauth.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class LoggableEntity<T extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private T id;
    @NotNull
    @EqualsAndHashCode.Exclude
    @JsonProperty("created")
    private LocalDateTime created;
    @NotNull
    @Column(name = "last_updated")
    @EqualsAndHashCode.Exclude
    @JsonProperty("lastUpdated")
    private LocalDateTime lastUpdated;
}
