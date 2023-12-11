package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(teams);
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long idTeam) {
        return teamRepository.findById(idTeam)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamRepository.save(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @PutMapping("/{idTeam}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long idTeam, @RequestBody Team team) {
        team.setIdTeam(idTeam);
        Team updatedTeam = teamRepository.save(team);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{idTeam}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long idTeam) {
        try {
            teamRepository.deleteById(idTeam);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
