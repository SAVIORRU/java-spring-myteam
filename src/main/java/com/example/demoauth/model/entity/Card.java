package com.example.demoauth.model.entity;

import com.example.demoauth.service.util.EntityIdResolver;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        resolver = EntityIdResolver.class,
        scope = Card.class
)
public class Card extends LoggableEntity<Long> {


    @JsonProperty("title")
    @NotNull
    @EqualsAndHashCode.Exclude
    private String title;

    @JsonProperty("description")
    @EqualsAndHashCode.Exclude
    private String description;

    @JsonProperty("endDate")
    @EqualsAndHashCode.Exclude
    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonProperty("team")
    @JsonIdentityReference(alwaysAsId = true)
    private Team team;


}
