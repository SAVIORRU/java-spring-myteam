package com.example.demoauth.controller;

import com.example.demoauth.model.entity.Team;
import com.example.demoauth.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TeamController extends LoggableEntityController<Team, Long> {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        super(teamService);
        this.teamService = teamService;
    }
}
