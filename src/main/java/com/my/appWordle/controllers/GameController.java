package com.my.appWordle.controllers;

import com.my.appWordle.error.GameNotFoundException;
import com.my.appWordle.models.Game;
import com.my.appWordle.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        // Si se proporcionan parámetros de paginación, obtener juegos paginados
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Game> games = gameService.getAllGamesWithPagination(pageRequest);
        return games.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(games);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGamesList() {
        // Se obtiene la lista de todos los juegos
        List<Game> games = gameService.getAllGames();
        return getResponseEntityForList(games);
    }


    @GetMapping("/{idGame}")
    public ResponseEntity<Game> getGameById(@PathVariable Long idGame) {
        try {
            Game game = gameService.getGameById(idGame)
                    .orElseThrow(() -> new GameNotFoundException(idGame));

            return ResponseEntity.ok().body(game);
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



