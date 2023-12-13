package com.my.appWordle.controllers;

import com.my.appWordle.models.Difficulty;
import com.my.appWordle.models.Game;
import com.my.appWordle.services.GameService;
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
class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void getAllGames() {
        // Arrange
        Game gameTest1 = createTestGame(5, "An example of a description 1", Difficulty.NORMAL);
        Game gameTest2 = createTestGame(3, "An example of a description 2", Difficulty.HARD);

        List<Game> games = Arrays.asList(gameTest1, gameTest2);
        when(gameService.getAllGames()).thenReturn(games);

        // Act
        ResponseEntity<List<Game>> responseEntity = gameController.getAllGames();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(games, responseEntity.getBody());
    }

    @Test
    void getGameById() {
        // Arrange
        Long gameId = 1L;
        Game game = createTestGame(7, "An example of a description 3", Difficulty.EASY);
        when(gameService.getGameById(gameId)).thenReturn(Optional.of(game));

        // Act
        ResponseEntity<Game> responseEntity = gameController.getGameById(gameId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(game, responseEntity.getBody());
    }

    @Test
    void getGameById_NotFound() {
        // Arrange
        Long gameId = 400L;
        when(gameService.getGameById(gameId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Game> responseEntity = gameController.getGameById(gameId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createGame() {
        // Arrange
        Game testGame2 = createTestGame(6, "An example of a description 4", Difficulty.NORMAL);

        when(gameService.createGame(any(Game.class))).thenReturn(testGame2);

        // Act
        ResponseEntity<Game> responseEntity = gameController.createGame(testGame2);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testGame2, responseEntity.getBody());
    }

    @Test
    void updateGame() {
        // Arrange
        Long gameId = 1L;
        Game existingGame = createTestGame(5, "An example of a description 5", Difficulty.HARD);
        Game updatedGame = createTestGame(8, "Updated description", Difficulty.EASY);

        lenient().when(gameService.getGameById(gameId)).thenReturn(Optional.of(existingGame));
        lenient().when(gameService.updateGame(gameId, updatedGame)).thenReturn(updatedGame);

        // Act
        ResponseEntity<Game> responseEntity = gameController.updateGame(gameId, updatedGame);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedGame, responseEntity.getBody());
    }

    @Test
    void deleteGame() {
        // Arrange
        Long gameId = 1L;
        Game existingGame = createTestGame(5, "An example of a description 6", Difficulty.NORMAL);

        lenient().when(gameService.getGameById(gameId)).thenReturn(Optional.of(existingGame));

        // Act
        ResponseEntity<Void> responseEntity = gameController.deleteGame(gameId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        verify(gameService, times(1)).deleteGame(gameId);
    }


    private Game createTestGame(int maxTries, String description, Difficulty difficulty) {
        Game testGame = new Game();

        testGame.setMaxTries(maxTries);
        testGame.setDescription(description);
        testGame.setDifficulty(difficulty);

        return testGame;
    }
}
