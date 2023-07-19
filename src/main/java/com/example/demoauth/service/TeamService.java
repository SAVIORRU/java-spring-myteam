package com.example.demoauth.service;

import com.example.demoauth.model.entity.Team;
import com.example.demoauth.model.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;



@Service
public class TeamService extends LoggableEntityService<Team, Long> {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository, ObjectMapper objectMapper) {
        super(teamRepository, objectMapper);
        this.teamRepository = teamRepository;
    }
}
