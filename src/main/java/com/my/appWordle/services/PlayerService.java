package com.my.appWordle.services;

import com.my.appWordle.error.PlayerNotFoundException;
import com.my.appWordle.models.Player;
import com.my.appWordle.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long idPlayer) {
        return playerRepository.findById(idPlayer)
                .orElseThrow(() -> new PlayerNotFoundException(idPlayer));
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long idPlayer, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(idPlayer);

        existingPlayer.setUserName(updatedPlayer.getUserName());
        existingPlayer.setScore(updatedPlayer.getScore());
        existingPlayer.setAvatarImg(updatedPlayer.getAvatarImg());

        return playerRepository.save(existingPlayer);
    }

    public void deletePlayer(Long idPlayer) {
        // Comprobación de si el jugador existe antes de intentar eliminarlo
        Player existingPlayer = getPlayerById(idPlayer);

        playerRepository.delete(existingPlayer);
    }
}
