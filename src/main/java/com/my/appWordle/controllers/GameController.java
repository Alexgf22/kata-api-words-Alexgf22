package com.my.appWordle.controllers;

import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
    }

    @GetMapping("/{idGame}")
    public ResponseEntity<Game> getGameById(@PathVariable Long idGame) {
        Game game = gameRepository.findById(idGame).orElse(null);
        if (game != null) {
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameRepository.save(game);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

    @PutMapping("/{idGame}")
    public ResponseEntity<Game> updateGame(@PathVariable Long idGame, @RequestBody Game game) {
        game.setIdGame(idGame);
        Game updatedGame = gameRepository.save(game);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    @DeleteMapping("/{idGame}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long idGame) {
        Optional<Game> gameOptional = gameRepository.findById(idGame);

        if (gameOptional.isPresent()) {
            gameRepository.deleteById(idGame);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
