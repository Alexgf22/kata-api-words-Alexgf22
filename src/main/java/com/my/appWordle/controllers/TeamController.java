package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teams, HttpStatus.OK);
        }
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long idTeam) {
        Team team = teamRepository.findById(idTeam).orElse(null);

        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam = teamRepository.save(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{idTeam}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long idTeam, @RequestBody Team team) {
        team.setIdTeam(idTeam);
        Team updatedTeam = teamRepository.save(team);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{idTeam}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long idTeam) {
        if (teamRepository.existsById(idTeam)) {
            teamRepository.deleteById(idTeam);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
