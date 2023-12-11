package com.my.appWordle.controllers;

import com.my.appWordle.models.Player;
import com.my.appWordle.models.Team;
import com.my.appWordle.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerController playerController;

    @Test
    void getAllPlayers() {
        // Arrange
        Player player1 = createTestPlayer("User1", 100, null, createTestTeam("TeamName 1", 90, new byte[]{/*imagen en bytes*/}));
        Player player2 = createTestPlayer("User2", 90, null, createTestTeam("TeamName 2", 80, new byte[]{/*imagen en bytes*/}));
        List<Player> players = Arrays.asList(player1, player2);
        when(playerRepository.findAll()).thenReturn(players);

        // Act
        ResponseEntity<List<Player>> responseEntity = playerController.getAllPlayers();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(players, responseEntity.getBody());
    }

    @Test
    void getPlayerById() {
        // Arrange
        Long idPlayer = 1L;
        Player player = createTestPlayer("TestUser", 80, null, createTestTeam("TeamName 3", 100, new byte[]{/*imagen en bytes*/}));
        when(playerRepository.findById(idPlayer)).thenReturn(Optional.of(player));

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
        when(playerRepository.findById(idPlayer)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Player> responseEntity = playerController.getPlayerById(idPlayer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createPlayer() {
        // Arrange
        Player testPlayer = createTestPlayer("TestUser", 100, null, createTestTeam("TeamName 4", 70, new byte[]{/*imagen en bytes*/}));
        when(playerRepository.save(any(Player.class))).thenReturn(testPlayer);

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

        lenient().when(playerRepository.findById(idPlayer)).thenReturn(Optional.of(existingPlayer));
        lenient().when(playerRepository.save(any(Player.class))).thenReturn(updatedPlayer);

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

        lenient().when(playerRepository.existsById(idPlayer)).thenReturn(true);
        lenient().when(playerRepository.findById(idPlayer)).thenReturn(Optional.of(existingPlayer));

        // Act
        ResponseEntity<Void> responseEntity = playerController.deletePlayer(idPlayer);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(playerRepository, times(1)).deleteById(idPlayer);
    }

    @Test
    void deletePlayer_NotFound() {
        // Arrange
        Long idPlayer = 600L;
        lenient().when(playerRepository.existsById(idPlayer)).thenReturn(false);

        // Act
        ResponseEntity<Void> responseEntity = playerController.deletePlayer(idPlayer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(playerRepository, never()).deleteById(idPlayer);
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
