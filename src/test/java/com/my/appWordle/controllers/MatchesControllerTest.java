package com.my.appWordle.controllers;

import com.my.appWordle.models.*;
import com.my.appWordle.repositories.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchesControllerTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchesController matchesController;

    @Test
    void getAllMatches() {
        // Arrange
        Matches matches1 = createTestMatch("Word1", 100, 3, new Date(), createTestPlayer("TestUser 1", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 1", 80, new byte[]{/*imagen en bytes*/})), createTestGame(1L,5, "An example of a description 1", Difficulty.NORMAL));
        Matches matches2 = createTestMatch("Word2", 90, 4, new Date(), createTestPlayer("TestUser 2", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 2", 90, new byte[]{/*imagen en bytes*/})), createTestGame(2L,6, "An example of a description 2", Difficulty.EASY));

        List<Matches> matches = Arrays.asList(matches1, matches2);
        when(matchRepository.findAll()).thenReturn(matches);

        // Act
        ResponseEntity<List<Matches>> responseEntity = matchesController.getAllMatches();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(matches, responseEntity.getBody());
    }

    @Test
    void getMatchById() {
        // Arrange
        Long idMatch = 1L;
        Matches matches = createTestMatch("Word3", 80, 4, new Date(), createTestPlayer("TestUser 3", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 3", 80, new byte[]{/*imagen en bytes*/})), createTestGame(1L,6, "An example of a description 3", Difficulty.NORMAL));
        when(matchRepository.findById(idMatch)).thenReturn(Optional.of(matches));

        // Act
        ResponseEntity<Matches> responseEntity = matchesController.getMatchById(idMatch);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(matches, responseEntity.getBody());
    }

    @Test
    void getMatchById_NotFound() {
        // Arrange
        Long idMatch = 1L;
        Optional<Matches> matchPrueba = matchRepository.findById(idMatch);
        System.out.println(matchPrueba);
        when(matchRepository.findById(idMatch)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Matches> responseEntity = matchesController.getMatchById(idMatch);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createMatch() {
        // Arrange
        Matches testMatches = createTestMatch("Word4", 100, 3, new Date(), createTestPlayer("TestUser 4", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 4", 70, new byte[]{/*imagen en bytes*/})), createTestGame(3L,7, "An example of a description 4", Difficulty.NORMAL));

        when(matchRepository.save(any(Matches.class))).thenReturn(testMatches);

        // Act
        ResponseEntity<Matches> responseEntity = matchesController.createMatch(testMatches);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testMatches, responseEntity.getBody());
    }

    @Test
    void updateMatch() {
        // Arrange
        Long idMatch = 2L;
        Matches existingMatches = createTestMatch("Word5", 100, 3, new Date(), createTestPlayer("TestUser 5", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 5", 80, new byte[]{/*imagen en bytes*/})), createTestGame(4L,6, "An example of a description 5", Difficulty.NORMAL));
        Matches updatedMatches = createTestMatch("Word6", 90, 4, new Date(), createTestPlayer("TestUser 6", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 6", 90, new byte[]{/*imagen en bytes*/})), createTestGame(5L,5, "An example of a description 6", Difficulty.EASY));


        lenient().when(matchRepository.findById(idMatch)).thenReturn(Optional.of(existingMatches));
        lenient().when(matchRepository.save(any(Matches.class))).thenReturn(updatedMatches);

        // Act
        ResponseEntity<Matches> responseEntity = matchesController.updateMatch(idMatch, updatedMatches);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedMatches, responseEntity.getBody());
    }

    @Test
    void deleteMatch() {
        // Arrange
        Long idMatch = 1L;
        Matches existingMatches = createTestMatch("Word7", 100, 3, new Date(), createTestPlayer("TestUser 7", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 7", 100, new byte[]{/*imagen en bytes*/})), createTestGame(1L,5, "An example of a description 7", Difficulty.HARD));

        when(matchRepository.findById(idMatch)).thenReturn(Optional.of(existingMatches));

        // Act
        ResponseEntity<Void> responseEntity = matchesController.deleteMatch(idMatch);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(matchRepository, times(1)).deleteById(idMatch);
    }



    @Test
    void deleteMatch_NotFound() {
        // Arrange
        Long idMatch = 400L;
        lenient().when(matchRepository.findById(idMatch)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> responseEntity = matchesController.deleteMatch(idMatch);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(matchRepository, never()).deleteById(idMatch);
    }


    private Matches createTestMatch(String word, Integer score, Integer nTries, Date dateTime, Player player, Game game) {
        Matches testMatches = new Matches();
        testMatches.setWord(word);
        testMatches.setScore(score);
        testMatches.setnTries(nTries);
        testMatches.setDateTime(dateTime);
        testMatches.setPlayer(player);
        testMatches.setGame(game);

        return testMatches;
    }

    private Team createTestTeam(String teamName, Integer score, byte[] badge) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setScore(score);
        team.setBadge(badge);
        return team;
    }


    private Player createTestPlayer(String userName, Integer score, byte[] avatarImg, Team team) {
        Player testPlayer = new Player();
        testPlayer.setUserName(userName);
        testPlayer.setScore(score);
        testPlayer.setAvatarImg(avatarImg);
        testPlayer.setTeam(team);
        return testPlayer;
    }


    private Game createTestGame(Long idGame, int maxTries, String description, Difficulty difficulty) {
        Game testGame = new Game();
        testGame.setIdGame(idGame);
        testGame.setMaxTries(maxTries);
        testGame.setDescription(description);
        testGame.setDifficulty(difficulty);
        return testGame;
    }


}
