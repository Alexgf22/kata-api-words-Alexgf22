package com.my.appWordle.controllers;

import com.my.appWordle.models.Team;
import com.my.appWordle.services.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void getAllTeamsWithPagination() {
        // Arrange
        Team team1 = createTestTeam("TeamName 1", 90, new byte[]{/*imagen en bytes*/});
        Team team2 = createTestTeam("TeamName 2", 80, new byte[]{/*imagen en bytes*/});
        List<Team> teams = Arrays.asList(team1, team2);

        // Configura el servicio para devolver una página en lugar de una lista
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Team> pagedTeams = new PageImpl<>(teams, pageRequest, teams.size());
        when(teamService.getAllTeamsWithPagination(pageRequest)).thenReturn(pagedTeams);

        // Act
        ResponseEntity<Page<Team>> responseEntity = (ResponseEntity<Page<Team>>) teamController.getAllTeams(0, 10);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica si el cuerpo de la respuesta es una página de equipos
        Page<Team> responseBody = responseEntity.getBody();
        Assertions.assertTrue(responseBody != null && !responseBody.isEmpty(), "La lista de equipos no debería estar vacía");


        // Verifica explícitamente que la lista de contenidos no esté vacía
        assertFalse("La lista de equipos no debería estar vacía", responseBody.isEmpty());

        // Verifica que la lista de contenidos sea igual a la lista esperada
        assertEquals(teams, responseBody.getContent());

        // Verifica otras propiedades de la página
        assertEquals(0, responseBody.getNumber(), "Número de página incorrecto");
        assertEquals(10, responseBody.getSize(), "Tamaño de página incorrecto");
        assertEquals(teams.size(), responseBody.getTotalElements(), "Número total de elementos incorrecto");
        assertEquals(1, responseBody.getTotalPages(), "Número total de páginas incorrecto");
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
