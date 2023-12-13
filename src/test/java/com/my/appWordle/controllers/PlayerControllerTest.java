package com.my.appWordle.controllers;

import com.my.appWordle.models.Player;
import com.my.appWordle.models.Team;
import com.my.appWordle.services.PlayerService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    void getAllPlayersWithPagination() {
        // Arrange
        Player player1 = createTestPlayer("User1", 100, null, createTestTeam("TeamName 1", 90, new byte[]{/*imagen en bytes*/}));
        Player player2 = createTestPlayer("User2", 90, null, createTestTeam("TeamName 2", 80, new byte[]{/*imagen en bytes*/}));
        List<Player> players = Arrays.asList(player1, player2);

        // Configura el servicio para devolver una página en lugar de una lista
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Player> pagedPlayers = new PageImpl<>(players, pageRequest, players.size());
        when(playerService.getAllPlayersWithPagination(pageRequest)).thenReturn(pagedPlayers);

        // Act
        ResponseEntity<Page<Player>> responseEntity = (ResponseEntity<Page<Player>>) playerController.getAllPlayers(0, 10);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifica si el cuerpo de la respuesta es una página de jugadores
        Page<Player> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        // Verifica explícitamente que la lista de contenidos no esté vacía
        assertFalse(responseBody.isEmpty(), "La lista de jugadores no debería estar vacía");

        // Verifica que la lista de contenidos sea igual a la lista esperada
        assertEquals(players, responseBody.getContent());

        // Verifica otras propiedades de la página según sea necesario
        assertEquals(0, responseBody.getNumber());
        assertEquals(10, responseBody.getSize());
        assertEquals(players.size(), responseBody.getTotalElements());
    }





    @Test
    void getPlayerById() {
        // Arrange
        Long idPlayer = 1L;
        Player player = createTestPlayer("TestUser", 80, null, createTestTeam("TeamName 3", 100, new byte[]{/*imagen en bytes*/}));
        when(playerService.getPlayerById(idPlayer)).thenReturn(Optional.of(player));

        // Act
        ResponseEntity<Player> responseEntity = playerController.getPlayerById(idPlayer);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(player, responseEntity.getBody());
    }

    @Test
    void getPlayerById_NotFound() {
        // Arrange
        Long idPlayer = 500L;
        when(playerService.getPlayerById(idPlayer)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Player> responseEntity = playerController.getPlayerById(idPlayer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createPlayer() {
        // Arrange
        Player testPlayer = createTestPlayer("TestUser", 100, null, createTestTeam("TeamName 4", 70, new byte[]{/*imagen en bytes*/}));
        when(playerService.createPlayer(any(Player.class))).thenReturn(testPlayer);

        // Act
        ResponseEntity<Player> responseEntity = playerController.createPlayer(testPlayer);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testPlayer, responseEntity.getBody());
    }

    @Test
    void updatePlayer() {
        // Arrange
        Long idPlayer = 1L;
        Player existingPlayer = createTestPlayer("TestUser", 80, null, createTestTeam("TeamName 5", 80, new byte[]{/*imagen en bytes*/}));
        Player updatedPlayer = createTestPlayer("UpdatedUser", 90, null, createTestTeam("TeamName 6", 90, new byte[]{/*imagen en bytes*/}));

        lenient().when(playerService.getPlayerById(idPlayer)).thenReturn(Optional.of(existingPlayer));
        when(playerService.updatePlayer(eq(idPlayer), any(Player.class))).thenReturn(updatedPlayer);

        // Act
        ResponseEntity<Player> responseEntity = playerController.updatePlayer(idPlayer, updatedPlayer);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedPlayer, responseEntity.getBody());
    }

    @Test
    void deletePlayer() {
        // Arrange
        Long idPlayer = 1L;
        Player existingPlayer = createTestPlayer("TestUser", 80, null, createTestTeam("TeamName 7", 80, new byte[]{/*imagen en bytes*/}));

        lenient().when(playerService.getPlayerById(idPlayer)).thenReturn(Optional.of(existingPlayer));

        // Act
        ResponseEntity<Void> responseEntity = playerController.deletePlayer(idPlayer);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(playerService, times(1)).deletePlayer(idPlayer);
    }


    private Player createTestPlayer(String userName, Integer score, byte[] avatarImg, Team team) {
        Player testPlayer = new Player();
        testPlayer.setUserName(userName);
        testPlayer.setScore(score);
        testPlayer.setAvatarImg(avatarImg);
        testPlayer.setTeam(team);
        return testPlayer;
    }

    private Team createTestTeam(String teamName, Integer score, byte[] badge) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setScore(score);
        team.setBadge(badge);
        return team;
    }
}
