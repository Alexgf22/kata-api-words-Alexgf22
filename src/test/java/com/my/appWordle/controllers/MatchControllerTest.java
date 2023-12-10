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
class MatchControllerTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchController matchController;

    @Test
    void getAllMatches() {
        // Arrange
        Match match1 = createTestMatch("Word1", 100, 3, new Date(), createTestPlayer("TestUser 1", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 1", 80, new byte[]{/*imagen en bytes*/})), createTestGame(1L,5, "An example of a description 1", Difficulty.NORMAL));
        Match match2 = createTestMatch("Word2", 90, 4, new Date(), createTestPlayer("TestUser 2", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 2", 90, new byte[]{/*imagen en bytes*/})), createTestGame(2L,6, "An example of a description 2", Difficulty.EASY));

        List<Match> matches = Arrays.asList(match1, match2);
        when(matchRepository.findAll()).thenReturn(matches);

        // Act
        ResponseEntity<List<Match>> responseEntity = matchController.getAllMatches();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(matches, responseEntity.getBody());
    }

    @Test
    void getMatchById() {
        // Arrange
        Long matchId = 1L;
        Match match = createTestMatch("Word3", 80, 4, new Date(), createTestPlayer("TestUser 3", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 3", 80, new byte[]{/*imagen en bytes*/})), createTestGame(1L,6, "An example of a description 3", Difficulty.NORMAL));
        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));

        // Act
        ResponseEntity<Match> responseEntity = matchController.getMatchById(matchId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(match, responseEntity.getBody());
    }

    @Test
    void getMatchById_NotFound() {
        // Arrange
        Long matchId = 1L;
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Match> responseEntity = matchController.getMatchById(matchId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createMatch() {
        // Arrange
        Match testMatch = createTestMatch("Word4", 100, 3, new Date(), createTestPlayer("TestUser 4", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 4", 70, new byte[]{/*imagen en bytes*/})), createTestGame(3L,7, "An example of a description 4", Difficulty.NORMAL));

        when(matchRepository.save(any(Match.class))).thenReturn(testMatch);

        // Act
        ResponseEntity<Match> responseEntity = matchController.createMatch(testMatch);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testMatch, responseEntity.getBody());
    }

    @Test
    void updateMatch() {
        // Arrange
        Long matchId = 1L;
        Match existingMatch = createTestMatch("Word5", 100, 3, new Date(), createTestPlayer("TestUser 5", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 5", 80, new byte[]{/*imagen en bytes*/})), createTestGame(4L,6, "An example of a description 5", Difficulty.NORMAL));
        Match updatedMatch = createTestMatch("Word6", 90, 4, new Date(), createTestPlayer("TestUser 6", 90, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 6", 90, new byte[]{/*imagen en bytes*/})), createTestGame(5L,5, "An example of a description 6", Difficulty.EASY));


        lenient().when(matchRepository.findById(matchId)).thenReturn(Optional.of(existingMatch));
        lenient().when(matchRepository.save(any(Match.class))).thenReturn(updatedMatch);

        // Act
        ResponseEntity<Match> responseEntity = matchController.updateMatch(matchId, updatedMatch);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedMatch, responseEntity.getBody());
    }

    @Test
    void deleteMatch() {
        // Arrange
        Long matchId = 1L;
        Match existingMatch = createTestMatch("Word7", 100, 3, new Date(), createTestPlayer("TestUser 7", 100, new byte[]{/*imagen en bytes*/}, createTestTeam("TeamName 7", 100, new byte[]{/*imagen en bytes*/})), createTestGame(1L,5, "An example of a description 7", Difficulty.HARD));

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(existingMatch));

        // Act
        ResponseEntity<Void> responseEntity = matchController.deleteMatch(matchId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(matchRepository, times(1)).deleteById(matchId);
    }


    @Test
    void deleteMatch_NotFound() {
        // Arrange
        Long matchId = 2L;
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> responseEntity = matchController.deleteMatch(matchId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(matchRepository, never()).deleteById(matchId);
    }

    private Match createTestMatch(String word, Integer score, Integer nTries, Date dateTime, Player player, Game game) {
        Match testMatch = new Match();
        testMatch.setWord(word);
        testMatch.setScore(score);
        testMatch.setnTries(nTries);
        testMatch.setDateTime(dateTime);
        testMatch.setPlayer(player);
        testMatch.setGame(game);

        return testMatch;
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
