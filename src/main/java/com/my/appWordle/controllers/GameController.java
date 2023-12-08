package com.my.appWordle.controllers;

import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{idGame}")
    public Game getGameById(@PathVariable Long idGame) {
        return gameRepository.findById(idGame).orElse(null);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/{idGame}")
    public Game updateGame(@PathVariable Long idGame, @RequestBody Game game) {
        game.setIdGame(idGame);
        return gameRepository.save(game);
    }

    @DeleteMapping("/{idGame}")
    public void deleteGame(@PathVariable Long idGame) {
        gameRepository.deleteById(idGame);
    }
}

