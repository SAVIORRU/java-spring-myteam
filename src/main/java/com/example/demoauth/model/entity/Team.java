package com.example.demoauth.model.entity;

import com.example.demoauth.service.util.EntityIdResolver;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope = Team.class
)
public class Team extends LoggableEntity<Long> {

    @NotNull
    @JsonProperty("size")
    @EqualsAndHashCode.Exclude
    private int size;

    @JsonProperty("description")
    @EqualsAndHashCode.Exclude
    private String description;


    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Card> cards;

}
