package com.my.appWordle.controllers;

import com.my.appWordle.models.Player;
import com.my.appWordle.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{idPlayer}")
    public Player getPlayerById(@PathVariable Long idPlayer) {
        return playerRepository.findById(idPlayer).orElse(null);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("/{idPlayer}")
    public Player updatePlayer(@PathVariable Long idPlayer, @RequestBody Player player) {
        player.setIdPlayer(idPlayer);
        return playerRepository.save(player);
    }

    @DeleteMapping("/{idPlayer}")
    public void deletePlayer(@PathVariable Long idPlayer) {
        playerRepository.deleteById(idPlayer);
    }
}

