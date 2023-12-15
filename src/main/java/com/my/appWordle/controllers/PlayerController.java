package com.my.appWordle.controllers;

import com.my.appWordle.models.Matches;
import com.my.appWordle.models.Player;
import com.my.appWordle.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> getAllPlayers(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size) {
        if (page > 0 || size > 0) {
            // Si se proporcionan parámetros de paginación, obtener jugadores paginados
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Player> players = playerService.getAllPlayersWithPagination(pageRequest);
            return ResponseEntity.ok(players);
        } else {
            // Si no se proporcionan parámetros, obtener todos los jugadores
            List<Player> players = playerService.getAllPlayers();
            return players.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(players);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayersList() {
        // Se obtiene la lista de todos los jugadores
        List<Player> players = playerService.getAllPlayers();
        return getResponseEntityForList(players);
    }

    private <T> ResponseEntity<List<T>> getResponseEntityForList(List<T> list) {
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }


    @GetMapping("/{idPlayer}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long idPlayer) {
        return playerService.getPlayerById(idPlayer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @PutMapping("/{idPlayer}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long idPlayer, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(idPlayer, player);
        return ResponseEntity.ok(updatedPlayer);
    }


    @DeleteMapping("/{idPlayer}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long idPlayer) {
        try {
            playerService.deletePlayer(idPlayer);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
