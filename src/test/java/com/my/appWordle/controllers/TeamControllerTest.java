package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.services.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void getAllTeams() {
        // Arrange
        Team team1 = createTestTeam("Team1", 100, new byte[]{/*imagen en bytes*/});
        Team team2 = createTestTeam("Team2", 90, new byte[]{/*imagen en bytes*/});

        List<Team> teams = Arrays.asList(team1, team2);
        when(teamService.getAllTeams()).thenReturn(teams);

        // Act
        ResponseEntity<List<Team>> responseEntity = teamController.getAllTeams();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(teams, responseEntity.getBody());
    }

    @Test
    void getTeamById() {
        // Arrange
        Long idTeam = 1L;
        Team team = createTestTeam("Team1", 100, new byte[]{/*imagen en bytes*/});
        when(teamService.getTeamById(idTeam)).thenReturn(team);

        // Act
        ResponseEntity<Team> responseEntity = teamController.getTeamById(idTeam);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(team, responseEntity.getBody());
    }

    @Test
    void getTeamById_NotFound() {
        // Arrange
        Long idTeam = 500L;
        when(teamService.getTeamById(idTeam)).thenReturn(null);

        // Act
        ResponseEntity<Team> responseEntity = teamController.getTeamById(idTeam);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createTeam() {
        // Arrange
        Team testTeam = createTestTeam("Team3", 80, new byte[]{/*imagen en bytes*/});
        when(teamService.createTeam(any(Team.class))).thenReturn(testTeam);

        // Act
        ResponseEntity<Team> responseEntity = teamController.createTeam(testTeam);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testTeam, responseEntity.getBody());
    }

    @Test
    void updateTeam() {
        // Arrange
        Long idTeam = 2L;
        Team existingTeam = createTestTeam("Team4", 100, new byte[]{/*imagen en bytes*/});
        Team updatedTeam = createTestTeam("UpdatedTeam", 90, new byte[]{/*imagen en bytes*/});

        lenient().when(teamService.getTeamById(idTeam)).thenReturn(existingTeam);
        when(teamService.updateTeam(eq(idTeam), any(Team.class))).thenReturn(updatedTeam);

        // Act
        ResponseEntity<Team> responseEntity = teamController.updateTeam(idTeam, updatedTeam);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedTeam, responseEntity.getBody());
    }

    @Test
    void deleteTeam() {
        // Arrange
        Long idTeam = 2L;
        Team existingTeam = createTestTeam("Team5", 100, new byte[]{/*imagen en bytes*/});

        when(teamService.getTeamById(idTeam)).thenReturn(existingTeam);

        // Act
        ResponseEntity<Void> responseEntity = teamController.deleteTeam(idTeam);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(teamService, times(1)).deleteTeam(idTeam);
    }

    @Test
    void deleteTeam_NotFound() {
        // Arrange
        Long idTeam = 400L;
        when(teamService.getTeamById(idTeam)).thenReturn(null);

        // Act
        ResponseEntity<Void> responseEntity = teamController.deleteTeam(idTeam);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(teamService, never()).deleteTeam(idTeam);
    }

    private Team createTestTeam(String teamName, Integer score, byte[] badge) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setScore(score);
        team.setBadge(badge);
        return team;
    }
}
