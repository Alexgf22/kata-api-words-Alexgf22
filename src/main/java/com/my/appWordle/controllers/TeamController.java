package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return teams.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(teams);
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long idTeam) {
        Team team = teamService.getTeamById(idTeam);

        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamService.createTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @PutMapping("/{idTeam}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long idTeam, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(idTeam, team);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{idTeam}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long idTeam) {
        Team existingTeam = teamService.getTeamById(idTeam);

        if (existingTeam != null) {
            teamService.deleteTeam(idTeam);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
