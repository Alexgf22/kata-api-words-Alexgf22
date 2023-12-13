package com.my.appWordle.services;

import com.my.appWordle.error.GameNotFoundException;
import com.my.appWordle.models.Game;
import com.my.appWordle.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Game> getGameById(Long idGame) {
        return gameRepository.findById(idGame);
    }


    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long idGame, Game updatedGame) {
        Optional<Game> existingGameOptional = getGameById(idGame);

        if (existingGameOptional.isPresent()) {
            Game existingGame = existingGameOptional.get();

            existingGame.setMaxTries(updatedGame.getMaxTries());
            existingGame.setDescription(updatedGame.getDescription());
            existingGame.setDifficulty(updatedGame.getDifficulty());

            return gameRepository.save(existingGame);
        } else {
            throw new GameNotFoundException(idGame);
        }
    }


    public void deleteGame(Long idGame) {
        // Comprobación de si el juego existe antes de intentar eliminarlo
        Optional<Game> existingGameOptional = getGameById(idGame);

        if (existingGameOptional.isPresent()) {
            gameRepository.delete(existingGameOptional.get());
        } else {
            throw new GameNotFoundException(idGame);
        }
    }


}
