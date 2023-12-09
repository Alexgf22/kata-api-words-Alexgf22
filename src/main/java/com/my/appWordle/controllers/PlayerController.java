package com.my.appWordle.controllers;

import com.my.appWordle.models.Player;
import com.my.appWordle.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
    }

    @GetMapping("/{idPlayer}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long idPlayer) {
        Player player = playerRepository.findById(idPlayer).orElse(null);

        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerRepository.save(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/{idPlayer}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long idPlayer, @RequestBody Player player) {
        player.setIdPlayer(idPlayer);
        Player updatedPlayer = playerRepository.save(player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{idPlayer}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long idPlayer) {
        if (playerRepository.existsById(idPlayer)) {
            playerRepository.deleteById(idPlayer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
