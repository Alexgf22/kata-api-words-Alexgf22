package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/{idTeam}")
    public Team getTeamById(@PathVariable Long idTeam) {
        return teamRepository.findById(idTeam).orElse(null);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/{idTeam}")
    public Team updateTeam(@PathVariable Long idTeam, @RequestBody Team team) {
        team.setIdTeam(idTeam);
        return teamRepository.save(team);
    }

    @DeleteMapping("/{idTeam}")
    public void deleteTeam(@PathVariable Long idTeam) {
        teamRepository.deleteById(idTeam);
    }
}

