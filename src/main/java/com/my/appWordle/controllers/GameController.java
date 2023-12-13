package com.my.appWordle.controllers;

import com.my.appWordle.error.GameNotFoundException;
import com.my.appWordle.models.Game;
import com.my.appWordle.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> getAllGames(@RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "10") int size) {
        if (page > 0 || size > 0) {
            // Si se proporcionan parámetros de paginación, obtener juegos paginados
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Game> games = gameService.getAllGamesWithPagination(pageRequest);
            return ResponseEntity.ok(games);
        } else {
            // Si no se proporcionan parámetros, obtener todos los juegos
            List<Game> games = gameService.getAllGames();
            return getResponseEntityForList(games);
        }
    }

    @GetMapping("/{idGame}")
    public ResponseEntity<Game> getGameById(@PathVariable Long idGame) {
        return gameService.getGameById(idGame)
                .map(game -> ResponseEntity.ok().body(game))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    @PutMapping("/{idGame}")
    public ResponseEntity<Game> updateGame(@PathVariable Long idGame, @RequestBody Game game) {
        if (idGame == null || game == null) {
            return ResponseEntity.badRequest().build();  // Bad request si idGame o game es nulo
        }

        game.setIdGame(idGame);
        Game updatedGame = gameService.updateGame(idGame, game);

        // Comprueba si el juego fue encontrado y actualizado
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idGame}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long idGame) {
        try {
            gameService.deleteGame(idGame);
            return ResponseEntity.noContent().build();
        } catch (GameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // Método de utilidad para construir ResponseEntity para listas
    private ResponseEntity<List<Game>> getResponseEntityForList(List<Game> resourceList) {
        return resourceList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(resourceList);
    }
}



