package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<?> getAllTeams(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size) {
        if (page > 0 || size > 0) {
            // Si se proporcionan parámetros de paginación, obtener equipos paginados
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Team> teams = teamService.getAllTeamsWithPagination(pageRequest);
            return ResponseEntity.ok(teams);
        } else {
            // Si no se proporcionan parámetros, obtener todos los equipos
            List<Team> teams = teamService.getAllTeams();
            return getResponseEntityForList(teams);
        }
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

    private ResponseEntity<?> getResponseEntityForList(List<?> resourceList) {
        return resourceList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(resourceList);
    }


}
