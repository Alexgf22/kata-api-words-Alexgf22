package com.my.appWordle.services;

import com.my.appWordle.error.GameNotFoundException;
import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game existingGame = getGameById(id);

        existingGame.setMaxTries(updatedGame.getMaxTries());
        existingGame.setDescription(updatedGame.getDescription());
        existingGame.setDifficulty(updatedGame.getDifficulty());

        return gameRepository.save(existingGame);
    }

    public void deleteGame(Long id) {
        // Comprobación de si el juego existe antes de intentar eliminarlo
        Game existingGame = getGameById(id);
        gameRepository.delete(existingGame);
    }
}
