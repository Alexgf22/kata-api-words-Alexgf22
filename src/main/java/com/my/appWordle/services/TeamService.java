package com.my.appWordle.services;

import com.my.appWordle.error.TeamNotFoundException;
import com.my.appWordle.models.Team;
import com.my.appWordle.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long idTeam) {
        return teamRepository.findById(idTeam)
                .orElseThrow(() -> new TeamNotFoundException(idTeam));
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long idTeam, Team updatedTeam) {
        Team existingTeam = getTeamById(idTeam);

        existingTeam.setScore(updatedTeam.getScore());
        existingTeam.setTeamName(updatedTeam.getTeamName());
        existingTeam.setBadge(updatedTeam.getBadge());

        return teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long idTeam) {
        // Comprobación de si el equipo existe antes de intentar eliminarlo
        Team existingTeam = getTeamById(idTeam);

        teamRepository.delete(existingTeam);
    }
}
