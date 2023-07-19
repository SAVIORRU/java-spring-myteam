package com.example.demoauth.model.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {

    @JsonProperty("title")
    Optional<String> title;

    @JsonProperty("description")
    Optional<String> description;

    @JsonProperty("endDate")
    Optional<LocalDateTime> endDate;

    @JsonProperty("team")
    Optional<Long> team;
}
