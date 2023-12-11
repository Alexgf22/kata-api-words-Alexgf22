package com.my.appWordle.controllers;

import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return getResponseEntityForList(games);
    }

    @GetMapping("/{idGame}")
    public ResponseEntity<Game> getGameById(@PathVariable Long idGame) {
        return gameRepository.findById(idGame)
                .map(game -> ResponseEntity.ok().body(game))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{idGame}")
    public ResponseEntity<Game> updateGame(@PathVariable Long idGame, @RequestBody Game game) {
        game.setIdGame(idGame);
        Game updatedGame = gameRepository.save(game);
        return ResponseEntity.ok().body(updatedGame);
    }

    @DeleteMapping("/{idGame}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long idGame) {
        return gameRepository.findById(idGame)
                .map(game -> {
                    gameRepository.deleteById(idGame);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método de utilidad para construir ResponseEntity para listas
    private ResponseEntity<List<Game>> getResponseEntityForList(List<Game> resourceList) {
        return resourceList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(resourceList);
    }
}



